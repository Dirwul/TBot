package main;

import com.pengrad.telegrambot.model.Update;

public class SingleUpdate {

    private static boolean isInteger(String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (!('0' <= c || c <= '9')) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDouble(String value) {
        boolean dotBefore = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '.' && !dotBefore) {
                dotBefore = true;
            } else if (!('0' <= c || c <= '9')) {
                return false;
            }
        }
        return true;
    }

    private static String baseResponseText() {
        Update update = (Update) Listener.updateIt.next();
        return update.message().text();
    }

    public static Info getCalcType(int[] fatherUpdateId) {
        String txt = baseResponseText();
        return switch (txt.toLowerCase()) {
            case "leftrectangle" -> new Info(CalculationType.LEFT_RECTANGLE);
            case "rightrectangle" -> new Info(CalculationType.RIGHT_RECTANGLE);
            case "trapezoid" -> new Info(CalculationType.TRAPEZOID);
            case "parabolic" -> new Info(CalculationType.PARABOLA);
            default -> new Info(); // null info
        };
    }

    public static Info getSectionType(int[] fatherUpdateId) {
        String txt = baseResponseText();
        return switch (txt.toLowerCase()) {
            case "split by quantity" -> new Info(SectionType.BY_STEPS);
            case "split by value" -> new Info(SectionType.BY_STEP_VALUE);
            default -> new Info(); // null info
        };
    }

    public static Info getSectionValue(int[] fatherUpdateId, SectionType type) {
        String txt = baseResponseText();
        if (type == SectionType.BY_STEPS && isInteger(txt)) { // todo to Exception throwing check
            return new Info(Integer.parseInt(txt));
        } else if (type == SectionType.BY_STEP_VALUE && isDouble(txt))  {
            return new Info(Double.parseDouble(txt));
        }
        return new Info(); // null info
    }
}
