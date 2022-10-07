package solve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private final static char UNDEFINED = '?';

    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isDot(char c) {
        return c == '.';
    }

    private static boolean isVariable(char c) {
        return c == 'x';
    }

    private static boolean isBraces(char c) {
        return c == '(' || c == ')';
    }

    private static boolean isControlOperator(char c) {
        return c == '[' || c == ']' || c == ';' || c == '{' || c == '}';
    }

    private static boolean isMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static boolean isOperator(char c) {
        return isDot(c) || isControlOperator(c) || isMathOperator(c);
    }

    private static final char[] sampleControlOperatorsArray = new char[]{ '[', ';', ']', '{', '}' };

    public static double[] getAdornedLimits(String expression) {
        int rightBracketId = expression.indexOf("]");
        return Arrays.stream(expression
                .substring(1, rightBracketId)
                .split(";"))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    public static List<String> getAdornedExpression(String expression) {
        int start = expression.indexOf("{") + 1;
        List<String> tokens = new ArrayList<>();
        int curToken = -1;
        int valueCounter = 0;
        // todo not choose '}'
        for (int i = start; i < expression.length(); i++) {
            char c = expression.charAt(i);
            char next = (i + 1 < expression.length() ? expression.charAt(i + 1) : '?');

            if (c == '-' && next == '(') {
                valueCounter = 0;
                tokens.add(String.valueOf(c) + String.valueOf(next));
                curToken++;
                i++;
            } else if ((isDigit(c) || isDot(c)) && valueCounter == 0) {
                curToken++;
                tokens.add(expression.substring(i, i+1));
                valueCounter++;
            } else if ((isDigit(c) || isDot(c)) && valueCounter > 0) {
                tokens.set(curToken, tokens.get(curToken) + c);
                valueCounter++;
            } else {
                valueCounter = 0;
                tokens.add(String.valueOf(c));
                curToken++;
            }
        }
        return tokens;
    }

    // complexity O(n)
    // todo parse x correctly
    // todo parse logarithms and trigonometry correct
    public static boolean isExpressionCorrect(String expression) {
        final int SIZE = 5;
        // check same count and type operators
        // not 2 dots or operators together
        // correct values (doubles) -> parse all
        // correct brackets

        List<String> values = new ArrayList<>();
        int bracesDiff = 0;
        char[] controlOperators = new char[SIZE];
        int[] controlOperatorsId = new int[SIZE];

        int operatorId = 0;
        char prev = UNDEFINED;
        int len = expression.length();
        int valueLenCounter = 0;

        for (int i = 0; i < len; i++) {
            char cur = expression.charAt(i);

            // correct symbol
            if (!(isOperator(cur) || isDigit(cur) || isBraces(cur) || isVariable(cur))) {
                System.out.println("Incorrect Symbol"); // debug
                return false;
            }
            // check same count and type operators
            if (isControlOperator(cur) && operatorId < SIZE) {
                controlOperators[operatorId] = cur;
                controlOperatorsId[operatorId] = i;
                operatorId++;
            }
            // parse values
            if (isDigit(cur) || isDot(cur)) {
                valueLenCounter++;
            } else if (valueLenCounter > 0) {
                values.add(expression.substring(i - valueLenCounter, i)); // todo maybe wrong (note: test and debug say ok)
                valueLenCounter = 0;
            }
            // correct brackets
            if (cur == '(') {
                bracesDiff++;
            } else if (bracesDiff > 0 && cur == ')') {
                bracesDiff--;
            } else if (bracesDiff == 0 && cur == ')'){
                System.out.println("Incorrect braces"); // debug
                return false;
            }
            // no 2 operators or variables together
            if (isOperator(cur) && isOperator(prev) && !(prev == ']' && cur == '{') &&
                    !(isOperator(prev) && cur == '-') && !(prev == '-') && cur == ('(') ||
                    prev == '(' && cur == ')' ||
                    isVariable(prev) && isVariable(cur) ||
                    isVariable(prev) && isDigit(cur) ||
                    isDigit(prev) && isVariable(cur)
            ) {
                System.out.println("2 operators together"); // debug
                return false;
            }

            prev = cur;
        }

        // check brackets if more than correct
        if (bracesDiff > 0) {
            System.out.println("Open braces more than close"); // debug
            return false;
        }
        // check operators
        if (!Arrays.equals(controlOperators, sampleControlOperatorsArray)) {
            System.out.println("Operators aren't equal"); // debug
            return false;
        }
        // control operators place
        if (!(controlOperatorsId[0] == 0 &&
                controlOperatorsId[4] == len - 1 &&
                controlOperatorsId[2] + 1 == controlOperatorsId[3])) {
            System.out.println("Operators aren't in position"); // debug
            return false;
        }
        // check values
        /* //debug
        int counter = 0;
        for (var value : values) {
            counter++;
            System.out.println(String.valueOf(counter) + " value = " + value);
        }
        return false;*/
        for (var value : values) {
            if (value.equals("x")) {
                continue;
            }
            if (!isDouble(value)) {
                System.out.println("Incorrect double value: " + value); // debug
                return false;
            }
        }

        return true;
    }

    public static boolean isDouble(String txt) {
        // todo is correct?
        try {
            Double.parseDouble(txt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isInteger(String txt) {
        // todo is correct?
        try {
            Integer.parseInt(txt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
