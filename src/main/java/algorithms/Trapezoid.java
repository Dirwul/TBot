package algorithms;

import languageRecognition.EvalVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class Trapezoid {

    public static double solve(double left, double right, int nSteps, ParseTree tree) {
        double stepValue = (right - left) / nSteps;
        double ans = 0;
        for (int i = 0; i < nSteps; i++) {
            double x1 = left + i * stepValue;
            double x2 = left + (i + 1) * stepValue;
            ans += 0.5 * (x2 - x1) * (
                    new EvalVisitor(x1).visit(tree) +
                    new EvalVisitor(x2).visit(tree)
            );
        }
        return ans;
    }
}
