package response;

import com.pengrad.telegrambot.model.request.*;

public class Keyboards {

    public static Keyboard test() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("Помощь").switchInlineQueryCurrentChat("/help"),
                new InlineKeyboardButton("Помощь").switchInlineQueryCurrentChat("/help"),
                new InlineKeyboardButton("Помощь").switchInlineQueryCurrentChat("/help"),
                new InlineKeyboardButton("Помощь").switchInlineQueryCurrentChat("/help")
        );
    }
    
    public static Keyboard help() { // todo ссылка на vk
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[][]{
                        new InlineKeyboardButton[] {
                                new InlineKeyboardButton("Помощь").switchInlineQueryCurrentChat("/help"),
                                new InlineKeyboardButton("Решить").switchInlineQueryCurrentChat("/solve")
                        },
                        new InlineKeyboardButton[] {
                                new InlineKeyboardButton("Выбрать численный метод").switchInlineQueryCurrentChat("/CalculationType")
                        },
                        new InlineKeyboardButton[] {
                                new InlineKeyboardButton("Выбрать тип разбиения").switchInlineQueryCurrentChat("/SectionType")
                        }
                }
        );
    }

    public static Keyboard calcType() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[][]{
                        new InlineKeyboardButton[] {
                                new InlineKeyboardButton("Пр-ков левых частей")
                                        .switchInlineQueryCurrentChat("/LeftRectangle"),
                                new InlineKeyboardButton("Пр-ков правых частей")
                                        .switchInlineQueryCurrentChat("/RightRectangle")
                        },
                        new InlineKeyboardButton[] {
                                new InlineKeyboardButton("Трапеций")
                                        .switchInlineQueryCurrentChat("/Trapezoid"),
                                new InlineKeyboardButton("Парабол")
                                        .switchInlineQueryCurrentChat("/Parabolic")
                        }
                }
        );
    }

    public static Keyboard sectionType() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[] {
                        new InlineKeyboardButton("Количество шагов")
                                .switchInlineQueryCurrentChat("/StepQuantity"),
                        new InlineKeyboardButton("Значение шага")
                                .switchInlineQueryCurrentChat("/StepValue")
                },
                new InlineKeyboardButton[] {
                        new InlineKeyboardButton("Точность вычисления")
                                .switchInlineQueryCurrentChat("/FloatingStep")
                }
        );
    }

    public static Keyboard afterCalcType() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[] {
                        new InlineKeyboardButton("Тип разбиения")
                                .switchInlineQueryCurrentChat("/SectionType"),
                        new InlineKeyboardButton("Решить")
                                .switchInlineQueryCurrentChat("/solve")
                }
        );
    }

    public static Keyboard afterSectionType() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[] {
                        new InlineKeyboardButton("Тип интегрирования")
                                .switchInlineQueryCurrentChat("/CalculationType"),
                        new InlineKeyboardButton("Решить")
                                .switchInlineQueryCurrentChat("/solve")
                }
        );
    }

}
