package main;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;

/*
new InlineKeyboardButton("callback_data").callbackData("callback_data"),
new InlineKeyboardButton("Switch!").switchInlineQuery("switch_inline_query")
*/

public class Keyboards {
    
    public static Keyboard help() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("help"),
                new InlineKeyboardButton("settings"),
                new InlineKeyboardButton("solve")
        );
    }

    public static Keyboard calcType() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("LeftRectangle"),
                new InlineKeyboardButton("RightRectangle"),
                new InlineKeyboardButton("Trapezoid"),
                new InlineKeyboardButton("Parabolic")
        );
    }

    public static Keyboard sectionType() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("Split by value"),
                new InlineKeyboardButton("Split by quantity")
        );
    }

    public static Keyboard sectionValueStep() { // not need, but....
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("0.01"),
                new InlineKeyboardButton("0.1"),
                new InlineKeyboardButton("1"),
                new InlineKeyboardButton("10")
        );
    }

    public static Keyboard sectionValueQuantity() { // not need, but....
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("100"),
                new InlineKeyboardButton("1000"),
                new InlineKeyboardButton("10000")
        );
    }

    public static Keyboard solve() { // not need but.....
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("Ok")
        );
    }
}
