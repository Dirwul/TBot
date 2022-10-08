package languageRecognition;

import structures.Info;

import java.util.Arrays;

public class Solver {

    public static double[] getAdornedLimits(String expression) {
        int rightBracketId = expression.indexOf("]");
        return Arrays.stream(expression
                        .substring(1, rightBracketId)
                        .split(";"))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    public static String run(Info userData, String expression) {
        // todo choose solvingType
        double[] limits = getAdornedLimits(expression);
        double dist = limits[1] - limits[0];

        double stepValue;
        int nSteps;
        if (userData.getStepValue() > Info.EPS) {
            stepValue = userData.getStepValue();
            nSteps = (int) (dist / stepValue);
        } else {
            nSteps = userData.getStepQuantity();
            stepValue = dist / nSteps;
        }

        int start = expression.indexOf("{") + 1;
        int end = expression.indexOf("}");

        double ans = 0;
        for (int i = 0; i < nSteps; i++) {
            double x1 = limits[0] + i * stepValue;
            double x2 = limits[0] + (i + 1) * stepValue;
            try {
                ans += 0.5 * (x2 - x1)
                        * (EvalSolver.run(x1, expression.substring(start, end))
                        + EvalSolver.run(x2, expression.substring(start, end))
                );
            } catch(Exception e) {
                return "Integral is not converge";
            }
        }
        return String.format("%.3f", ans);
    }
}
