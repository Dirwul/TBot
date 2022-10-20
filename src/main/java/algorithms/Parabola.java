package algorithms;

import languageRecognition.EvalVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class Parabola {

    public static double solve(double left, double right, int nSteps, ParseTree tree) {
        double stepValue = (right - left) / nSteps;
        double ans = 0;
        for (int i = 0; i < nSteps; i++) {
            double xLeft = left + i * stepValue;
            double xRight = left + (i + 1) * stepValue;
            double xMid = (xLeft + xRight) / 2;
            ans += (xRight - xLeft) * (new EvalVisitor(xLeft).visit(tree) +
                    4 * new EvalVisitor(xMid).visit(tree) +
                    new EvalVisitor(xRight).visit(tree)) / 6;
        }
        return ans;
    }
}
