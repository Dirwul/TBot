package Algorithms;

import languageRecognition.EvalVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class RightRectangle {

    public static double solve(double left, double right, int nSteps, ParseTree tree) {
        double stepValue = (right - left) / nSteps;
        double ans = 0;
        for (int i = 0; i < nSteps; i++) {
            double x = left + (i + 1) * stepValue;
            ans += stepValue * new EvalVisitor(x).visit(tree);
        }
        return ans;
    }
}
