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

    public static double solve(double leftX, double rightX, double leftY, double rightY, int nSteps, ParseTree tree) {
        double stepValueX = (rightX - leftX) / nSteps;
        double stepValueY = (rightY - leftY) / nSteps;
        double ans = 0;
        for (int iy = 0; iy < nSteps; iy++) {
            double yAns = 0;
            double y1 = leftY + iy * stepValueY;
            double y2 = leftY + (iy + 1) * stepValueY;
            for (int i = 0; i < nSteps; i++) {
                double x1 = leftX + i * stepValueX;
                double x2 = leftX + (i + 1) * stepValueX;
                ans += 0.5 * (x2 - x1) * (
                        new EvalVisitor(x1, y1).visit(tree)
                        + new EvalVisitor(x2, y2).visit(tree)
                );
            }
            ans += stepValueY * yAns;
        }
        return ans;
    }
}
