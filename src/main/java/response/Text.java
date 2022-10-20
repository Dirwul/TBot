package response;

public class Text {
    public static String help =
        """
            Ваш маленький помощник уже тут!
            1. Метод и отрезок интегрирования выбирайте из предложенных вариантов.
            2. Функцию и отрезок интегрирования вводите по следующим правилам:
                а) [a;b]{expression}
                b) a строго меньше b
                c) Допустимые операторы: + - * / ^
            3. Математическеи функции: sqrt(value), ln(value), log(base, value)
            4. Тригонометрические функции: sin(), cos(), tan(), asin(), acos(), atan()
            5. Значение тригонометрических функций принимается в радианах.
                Чтобы воспользоваться градусной мерой, используйте выражение (value * pi / 180)
            6. Доступные константы: pi, e
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
            Сначала выберите метод интегрирования и тип разбиения.
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
    public static String incorrectFloatingStep = "Некорректная точность";
}
