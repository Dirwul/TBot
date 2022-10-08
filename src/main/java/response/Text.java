package response;

public class Text {
    public static String help =
        """
            Ваш маленький помощник уже тут!
            1. Метод и отрезок интегрирования выбирайте из предложенных вариантов.
            2. Функцию и отрезок интегрирования вводите по следующим правилам:
                а) [a;b]{expression}
                б) a строго меньше b
                в) в выражении допустимы только операторы { +, -, *, /, ^ }
                д) Можно использовать sqrt(), ln(), log(base, value), sin(), cos(), tg(), ctg()
            3. Если есть баги, то я не виноват)0
        """;
    public static String calcType =
            """
            Выберите метод численного интегрирования:
            """;
    public static String sectionType =
            """
            Выберите как разбить отрезок интегрирования [a;b]:
            """;
    public static String solve =
            """
            Введите выражение в формате [a;b]{expr}
            Доступные символы: +, -, *, /, ^, (, )
            """;
    public static String chooseSettings =
            """
            !!!Этот раздел пока закрыт!!! todo
            Choose calculation type or section type before solving
            """;
    public static String undefinedCommand =
            """
            Такой команды не существует.
            """;
    public static String incorrectCalcType =
            """
            Вы ввели неизвестный метод численного интегрирования.
            """;
    public static String incorrectSectionType =
            """
            Вы выбрали неизвестный тип разбиения.
            """;
    public static String incorrectStepValue =
            """
            Некорректное значение шага. Воспользуйтесь командой /help для дополнительной информации.
            """;
    public static String incorrectStepQuantity =
            """
            Некорректное количество шагов. Воспользуйтесь командой /help для дополнительной информации.
            """;
    public static String incorrectExpression =
            """
            Некорректное значение выражения. Воспользуйтесь командой /help для дополнительной информации.
            """;
    public static String back = "";

    public static String correctRead = "Значение корректно";

    public static String inputData = "Введите значение:";
}
