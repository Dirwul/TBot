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

    public static double solve(double leftX, double rightX, double leftY, double rightY, int nSteps, ParseTree tree) {
        double stepValueX = (rightX - leftX) / nSteps;
        double stepValueY = (rightY - leftY) / nSteps;
        double ans = 0;
        for (int iy = 0; iy < nSteps; iy++) {
            double yAns = 0;

            double yLeft = leftY + iy * stepValueY;
            double yRight = leftY + (iy + 1) * stepValueY;
            double yMid = (yLeft + yRight) / 2;

            for (int i = 0; i < nSteps; i++) {
                double xLeft = leftX + i * stepValueX;
                double xRight = leftX + (i + 1) * stepValueX;
                double xMid = (xLeft + xRight) / 2;

                yAns += (xRight - xLeft) * (new EvalVisitor(xLeft, yLeft).visit(tree) +
                        4 * new EvalVisitor(xMid, yMid).visit(tree) +
                        new EvalVisitor(xRight, yRight).visit(tree)) / 6;
            }
            ans += stepValueY * yAns;
        }
        return ans;
    }
}
