package response;

public class Text {
    public static String help =
        """
            It's help.
            1) /help or /start
            2) /set_calc
            3) /set_sec
            4) /solve smth with command "solve [a;b]{expression}"
        """;
    public static String calcType =
            """
            Choose calculation type:
            /rectangles | /trapezoid | /parabolic
            """;
    public static String sectionType =
            """
            Choose how to split [a;b]
            /by_value | /by_quantity
            """;
    public static String solve =
            """
            TODO (solve)
            """;
    public static String chooseSettings =
            """
            Choose calculation type or section type before solving
            """;
    public static String undefinedCommand =
            """
            Undefined command!!! Use /help
            """;
    public static String incorrectCalcType =
            """
            IncorrectCalcType
            """;
    public static String incorrectSectionType =
            """
            IncorrectSectionType
            """;
    public static String incorrectStepValue =
            """
            IncorrectStepValue
            """;
    public static String incorrectStepQuantity =
            """
            IncorrectStepQuantity
            """;
    public static String incorrectExpression =
            """
            Incorrect Expression
            """;
}
