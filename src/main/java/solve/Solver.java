package solve;

import structures.Info;

import java.util.List;

public class Solver {

    public static String run(Info userData, String expression) {
        // todo choose solvingType
        double[] limits = Parser.getAdornedLimits(expression);
        List<String> tokens = Parser.getAdornedExpression(expression);
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

        System.out.println("Limits: " + limits[0] + " ; " + limits[1]);
        double ans = 0;
        for (int i = 0; i < nSteps; i++) {
            double x1 = limits[0] + i * stepValue;
            double x2 = limits[0] + (i + 1) * stepValue;
            ans += 0.5 * (x2 - x1) *
                    (ExpressionIterator.run(x1, tokens) +
                    ExpressionIterator.run(x2, tokens));
        }
        return String.format("%.3f", ans);
    }
}
