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

    private static double[] getAdornedLimits(String expression, int[] startId) {
        int rightBracketId = expression.indexOf("]", startId[0]);
        int leftBracketId = startId[0] + 1;
        startId[0] = rightBracketId + 1;
        return Arrays.stream(expression
                        .substring(leftBracketId, rightBracketId)
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
        int[] startId = new int[2];
        startId[0] = 0;

        double[] limits = getAdornedLimits(expression, startId);
        System.out.println("Первые лимитки ок");
        double[] limitsY = null;

        if (expression.charAt(startId[0]) == '[') {
            limitsY = getAdornedLimits(expression, startId);
            System.out.println("Вторые лимитки ок");
            return run2(userData, expression, limits, limitsY);
        }

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
            return "Вы не можете использовать переменные, отличные от 'x' и 'y'";
        }

        double ans = 0;

        if (userData.getSectionType() == SectionType.BY_FLOATING_STEP) {
            nSteps = 1;
            double ans1 = algorithms.Trapezoid.solve(left, right, nSteps, tree);
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
                    case LEFT_RECTANGLE -> algorithms.LeftRectangle.solve(left + leftIndent, right, nSteps, tree);
                    case RIGHT_RECTANGLE -> algorithms.RightRectangle.solve(left + leftIndent, right, nSteps, tree);
                    case TRAPEZOID -> algorithms.Trapezoid.solve(left + leftIndent, right, nSteps, tree);
                    case PARABOLA -> algorithms.Parabola.solve(left + leftIndent, right, nSteps, tree);
                };
                ans1 = upd;
                nSteps <<= 1;
            } while (abs(ans1 - ans) > userData.getEps());  //сравнение приближений с заданной точностью
        } else {
            ans = switch(userData.getCalcType()) {
                case LEFT_RECTANGLE -> algorithms.LeftRectangle.solve(left, right, nSteps, tree);
                case RIGHT_RECTANGLE -> algorithms.RightRectangle.solve(left, right, nSteps, tree);
                case TRAPEZOID -> algorithms.Trapezoid.solve(left, right, nSteps, tree);
                case PARABOLA -> algorithms.Parabola.solve(left, right, nSteps, tree);
            };
        }

        // output
        String ansDouble = String.format("%.9f", ans);
        String ansInteger = String.format("%.0f", ans);
        return ansDouble.endsWith(".000000000") ? ansInteger : ansDouble;
    }

    public static String run2(Info userData, String expression, double[] limitX, double[] limitY) {
        double leftX = limitX[0];
        double rightX = limitX[1];
        double leftY = limitY[0];
        double rightY = limitY[1];
        double distX = rightX - leftX;
        double distY = rightY - leftY;

        double stepValueX;
        int nSteps;
        if (userData.getStepValue() > Info.EPS) {
            stepValueX = userData.getStepValue();
            nSteps = (int) (distX / stepValueX);
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
        EvalVisitor visitorValidator = new EvalVisitor(leftX, leftY);
        visitorValidator.visit(tree);
        if (visitorValidator.memory.size() > 2) {
            return "Вы не можете использовать переменные, отличные от 'x' и 'y'";
        }

        double ans = 0;

        if (userData.getSectionType() == SectionType.BY_FLOATING_STEP) {
            nSteps = 1;
            double ans1 = algorithms.Trapezoid.solve(leftX, rightX, leftY, rightY, nSteps, tree);
            do {
                if (nSteps == (1 << 12)) {
                    return "К сожалению приведение к данной точности требует излишних вычислений.\n" +
                            "Воспользуйтесь стандартным шагом и количеством шагов 1e7.\n" +
                            "Примерный ответ: " + ans1 + "\n" +
                            "С примерной точностью: " + abs(ans1 - ans);
                }
                ans = ans1;     //второе приближение
                double leftIndentX = (rightX - leftX) / (nSteps * 2);
                double leftIndentY = (rightY - leftY) / (nSteps * 2);
                double upd = switch(userData.getCalcType()) {
                    case LEFT_RECTANGLE -> algorithms.LeftRectangle.solve(leftX, rightX, leftY, rightY, nSteps, tree);
                    case RIGHT_RECTANGLE -> algorithms.RightRectangle.solve(leftX, rightX, leftY, rightY, nSteps, tree);
                    case TRAPEZOID -> algorithms.Trapezoid.solve(leftX, rightX, leftY, rightY, nSteps, tree);
                    case PARABOLA -> algorithms.Parabola.solve(leftX, rightX, leftY, rightY, nSteps, tree);
                };
                ans1 = upd;
                nSteps <<= 1;
            } while (abs(ans1 - ans) > userData.getEps());  //сравнение приближений с заданной точностью
        } else {
            ans = switch(userData.getCalcType()) {
                case LEFT_RECTANGLE -> algorithms.LeftRectangle.solve(leftX, rightX, leftY, rightY, nSteps, tree);
                case RIGHT_RECTANGLE -> algorithms.RightRectangle.solve(leftX, rightX, leftY, rightY, nSteps, tree);
                case TRAPEZOID -> algorithms.Trapezoid.solve(leftX, rightX, leftY, rightY, nSteps, tree);
                case PARABOLA -> algorithms.Parabola.solve(leftX, rightX, leftY, rightY, nSteps, tree);
            };
        }

        // output
        String ansDouble = String.format("%.9f", ans);
        String ansInteger = String.format("%.0f", ans);
        return ansDouble.endsWith(".000000000") ? ansInteger : ansDouble;
    }
}
