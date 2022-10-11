package languageRecognition;

public class Validator {

    private static final double EPS = 1e-9;

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCorrectInteger(String value) {
        return isInteger(value) && Integer.parseInt(value) > 0;
    }

    public static boolean isCorrectDouble(String value) {
        return isDouble(value) && Double.parseDouble(value) > EPS;
    }

    public static boolean isCorrectExpression(String expr) {
        try {
            int sep = expr.indexOf("{");
            return isCorrectLimits(expr.substring(0, sep)) &&
                    isCorrectToEval(expr.substring(sep));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCorrectLimits(String expr) {
        try {
            int left = 0;
            int mid = expr.indexOf(";");
            int right = expr.length() - 1;
            double a = Double.parseDouble(expr.substring(left + 1, mid));
            double b = Double.parseDouble(expr.substring(mid + 1, right));
            return a + EPS < b && expr.charAt(left) == '[' && expr.charAt(right) == ']';
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCorrectToEval(String expr) {
        try {
            EvalSolver.run(1, expr); // todo to validate only [a]
            return expr.startsWith("{") && expr.endsWith("}");
        } catch(Exception e) {
            System.out.println("not correct to eval"); // debug
            return false;
        }
    }
}