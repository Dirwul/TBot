package main;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import static main.App.bot;

public class Snippet {

    public static void help(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.help)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true)
                .replyMarkup(Keyboards.help());
        bot.execute(msgToSend);
    }

    public static void calcType(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.calcType)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true)
                .replyMarkup(Keyboards.calcType());
        bot.execute(msgToSend);
    }

    public static void sectionType(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.sectionType)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true)
                        .replyMarkup(Keyboards.sectionType());
        bot.execute(msgToSend);
    }

    public static void sectionValue(Long chatId, SectionType type) {
        var msgToSend =
                new SendMessage(chatId, type == SectionType.BY_STEP_VALUE ?
                        Text.sectionValueStep : Text.sectionValueQuantity)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true)
                        .replyMarkup(type == SectionType.BY_STEP_VALUE ?
                                Keyboards.sectionValueStep() : Keyboards.sectionValueQuantity());
        bot.execute(msgToSend);
    }

    public static void solve(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.solve)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true)
                        .replyMarkup(Keyboards.solve());
        bot.execute(msgToSend);
    }

    public static void ErrorUndefinedCalcType(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.UndefinedCalcType)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true);
        bot.execute(msgToSend);
    }

    public static void ErrorUndefinedSecType(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.UndefinedSecType)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true);
        bot.execute(msgToSend);
    }

    public static void ErrorUndefinedSecValue(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.UndefinedSecValue)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true);
        bot.execute(msgToSend);
    }

    public static void ErrorIncorrectExpression(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.IncorrectExpression)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true);
        bot.execute(msgToSend);
    }
}
