package response;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import static main.App.bot;

public class Snippet {

    public static class Error {

        public static void chooseSettings(Long chatId) {
            var msgToSend =
                    new SendMessage(chatId, Text.chooseSettings)
                            .parseMode(ParseMode.HTML)
                            .disableWebPagePreview(true)
                            .disableNotification(true);
            bot.execute(msgToSend);
        }

        public static void undefinedCommand(Long chatId) {
            var msgToSend =
                    new SendMessage(chatId, Text.undefinedCommand)
                            .parseMode(ParseMode.HTML)
                            .disableWebPagePreview(true)
                            .disableNotification(true);
            bot.execute(msgToSend);
        }

        public static void incorrectCalcType(Long chatId) {
            var msgToSend =
                    new SendMessage(chatId, Text.incorrectCalcType)
                            .parseMode(ParseMode.HTML)
                            .disableWebPagePreview(true)
                            .disableNotification(true);
            bot.execute(msgToSend);
        }

        public static void incorrectSectionType(Long chatId) {
            var msgToSend =
                    new SendMessage(chatId, Text.incorrectSectionType)
                            .parseMode(ParseMode.HTML)
                            .disableWebPagePreview(true)
                            .disableNotification(true);
            bot.execute(msgToSend);
        }

        public static void incorrectStepValue(Long chatId) {
            var msgToSend =
                    new SendMessage(chatId, Text.incorrectStepValue)
                            .parseMode(ParseMode.HTML)
                            .disableWebPagePreview(true)
                            .disableNotification(true);
            bot.execute(msgToSend);
        }

        public static void incorrectStepQuantity(Long chatId) {
            var msgToSend =
                    new SendMessage(chatId, Text.incorrectStepQuantity)
                            .parseMode(ParseMode.HTML)
                            .disableWebPagePreview(true)
                            .disableNotification(true);
            bot.execute(msgToSend);
        }

        public static void incorrectExpression(Long chatId) {
            var msgToSend =
                    new SendMessage(chatId, Text.incorrectExpression)
                            .parseMode(ParseMode.HTML)
                            .disableWebPagePreview(true)
                            .disableNotification(true);
            bot.execute(msgToSend);
        }
    }

    public static void help(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.help)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
                //.replyMarkup(new ForceReply());
        // синхронно
        bot.execute(msgToSend);
    }

    public static void calcType(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.calcType)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
                //.replyMarkup(Keyboards.calcType());
        bot.execute(msgToSend);
    }

    public static void sectionType(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.sectionType)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true);
                        //.replyMarkup(Keyboards.sectionType());
        bot.execute(msgToSend);
    }

    public static void solve(Long chatId) {
        var msgToSend =
                new SendMessage(chatId, Text.solve)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true);
                        //.replyMarkup(Keyboards.solve());
        bot.execute(msgToSend);
    }
}
