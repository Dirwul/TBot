package main;

public class Text {
    public static String help =
            """
            It's help.
            1) /help
            2) /settings
            3) /solve smth with command "solve [a;b]{expression}"
            """;
    public static String calcType =
            """
            Choose calculation type:
            /rectangles | /trapezoid | /parabolic
            """;
    public static String sectionType =
            """
            Choose hor to split [a;b]
            """;

    public static String sectionValueStep =
            """
            Choose step-value
            """;

    public static String sectionValueQuantity =
            """
            Choose quantity of steps
            """;

    public static String solve =
            """
            TODO (solve)
            """;
    public static String UndefinedCalcType =
            """
            Undefined Calculation Type. Use /help
            """;
    public static String UndefinedSecType =
            """
            Undefined Section Type. Use /help
            """;
    public static String IncorrectExpression =
            """
            Incorrect Expression. Use /help
            """;
    public static String UndefinedSecValue =
            """
            Undefined Section Value. Use /help
            """;
}
