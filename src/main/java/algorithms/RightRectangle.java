package algorithms;

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

    public static double solve(double leftX, double rightX, double leftY, double rightY, int nSteps, ParseTree tree) {
        double stepValueX = (rightX - leftX) / nSteps;
        double stepValueY = (rightY - leftY) / nSteps;
        double ans = 0;
        for (int iy = 0; iy < nSteps; iy++) {
            double yAns = 0;
            double y = leftY + (iy + 1) * stepValueY;
            for (int i = 0; i < nSteps; i++) {
                double x = leftX + (i + 1) * stepValueX;
                yAns += stepValueX * new EvalVisitor(x, y).visit(tree);
            }
            ans += stepValueY * yAns;
        }
        return ans;
    }
}
