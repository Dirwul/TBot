package languageRecognition;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import structures.Info;
import structures.SectionType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.Math.abs;

public class Solver {

    private static double[] getAdornedLimits(String expression) {
        int rightBracketId = expression.indexOf("]");
        return Arrays.stream(expression
                        .substring(1, rightBracketId)
                        .split(";"))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    public static ParseTree getParseTree(String expression) throws Exception {
        expression = "%s\n".formatted(expression);

        InputStream is = new ByteArrayInputStream(expression
                .replaceAll("[{}]", "")
                .getBytes(StandardCharsets.UTF_8)
        );
        ANTLRInputStream input = new ANTLRInputStream(is);

        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);

        return parser.prog();
    }

    public static String run(Info userData, String expression) {
        // todo choose solvingType
        // get limits
        double[] limits = getAdornedLimits(expression);
        double left = limits[0];
        double right = limits[1];
        double dist = right - left;

        double stepValue;
        int nSteps;
        if (userData.getStepValue() > Info.EPS) {
            stepValue = userData.getStepValue();
            nSteps = (int) (dist / stepValue);
        } else {
            nSteps = userData.getStepQuantity();
        }

        // get expression tree
        int start = expression.indexOf("{") + 1;
        int end = expression.indexOf("}");
        ParseTree tree;
        try {
            tree = getParseTree(expression.substring(start, end));
        } catch (Exception e) {
            return "Smth gets wrong or Integral is not converge";
        }
        //debug
        EvalVisitor visitorValidator = new EvalVisitor(left);
        visitorValidator.visit(tree);
        if (visitorValidator.memory.size() > 1) {
            return "Вы не можете использовать переменные, отличные от 'x'";
        }

        double ans = 0;

        if (userData.getSectionType() == SectionType.BY_FLOATING_STEP) {
            nSteps = 1;
            //double ans1 = new EvalVisitor(left).visit(tree) * (right - left); // only left and right
            double ans1 = Algorithms.Trapezoid.solve(left, right, nSteps, tree);
            do {
                if (nSteps == (1 << 25)) {
                    return "К сожалению приведение к данной точности требует излишних вычислений.\n" +
                            "Воспользуйтесь стандартным шагом и количеством шагов 1e7.\n" +
                            "Примерный ответ: " + ans1 + "\n" +
                            "С примерной точностью: " + abs(ans1 - ans);
                }
                ans = ans1;     //второе приближение
                double leftIndent = (right - left) / (nSteps * 2);
                double upd = switch(userData.getCalcType()) {
                    case LEFT_RECTANGLE -> Algorithms.LeftRectangle.solve(left + leftIndent, right, nSteps, tree);
                    case RIGHT_RECTANGLE -> Algorithms.RightRectangle.solve(left + leftIndent, right, nSteps, tree);
                    case TRAPEZOID -> Algorithms.Trapezoid.solve(left + leftIndent, right, nSteps, tree);
                    case PARABOLA -> Algorithms.Parabola.solve(left + leftIndent, right, nSteps, tree);
                };
                ans1 = (ans + upd) / 2;
                nSteps <<= 1;
            }
            while (abs(ans1 - ans) > userData.getEps());  //сравнение приближений с заданной точностью
            ans = ans / 2 + ans1 / 2;
        } else {
            ans = switch(userData.getCalcType()) {
                case LEFT_RECTANGLE -> Algorithms.LeftRectangle.solve(left, right, nSteps, tree);
                case RIGHT_RECTANGLE -> Algorithms.RightRectangle.solve(left, right, nSteps, tree);
                case TRAPEZOID -> Algorithms.Trapezoid.solve(left, right, nSteps, tree);
                case PARABOLA -> Algorithms.Parabola.solve(left, right, nSteps, tree);
            };
        }

        // output
        String ansDouble = String.format("%.9f", ans);
        String ansInteger = String.format("%.0f", ans);
        return ansDouble.endsWith(".000000000") ? ansInteger : ansDouble;
    }
}
