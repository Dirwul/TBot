package solve;

import structures.Operator;

import java.util.List;

import static java.lang.Math.pow;

public class ExpressionIterator {

    private static int POSITIVE = 1;

    private static int NEGATIVE = -1;

    static double x;

    static List<String> tokens;

    static int i;

    static String token;

    private static void nextToken() {
        try {
            token = tokens.get(i);
            i++;
        } catch(Exception e) {
            token = null;
        }
    }

    private static String getNextToken() {
        try {
            String nextToken = tokens.get(i + 1);
            return nextToken;
        } catch (Exception e) {
            return null;
        }
    }

    private static double operate(double a, Operator operator, double b) {
        return switch (operator) {
            case PLUS -> a + b;
            case MINUS -> a - b;
            case MULTI -> a * b;
            case DIV -> a / b; // may throw exception
            case POW -> pow(a, b); // may throw exception
        };
    }

    private static double getExpressionResult(int sign) {
        double ans = 0;
        Operator state = Operator.PLUS;
        //System.out.println("tokens quantity: " + tokens.size()); //debug
        while (true) {
            nextToken();
            System.out.println("token = " + token); // debug

            if (token == null || token.equals("}")) {
                break;
            }

            if (token.equals("-(")) {
                ans = operate(ans, state, getExpressionResult(NEGATIVE));
            } else if (token.equals("(")) {
                ans = operate(ans, state, getExpressionResult(POSITIVE));
            } else if (token.equals(")")) {
                break;
            } else if (token.equals("x")) {
                ans = operate(ans, state, x);
            } else if (token.equals("+")) {
                state = Operator.PLUS;
            } else if (token.equals("-")) {
                state = Operator.MINUS;
            } else if (token.equals("*")) {
                state = Operator.MULTI;
            } else if (token.equals("/")) {
                state = Operator.DIV;
            } else if (token.equals("^")) {
                state = Operator.POW;
            } else if (Parser.isDouble(token)) {
                ans = operate(ans, state, Double.parseDouble(token));
            } else {
                System.out.println("Smth gets wrong in token read");
            }
        }
        System.out.println("При х = " + x + ", ans = " + sign * ans);
        return sign * ans;
    }

    public static double run(double x, List<String> tokens) {
        ExpressionIterator.x = x;
        ExpressionIterator.tokens = tokens;
        ExpressionIterator.i = 0;
        return getExpressionResult(POSITIVE);
    }
}
