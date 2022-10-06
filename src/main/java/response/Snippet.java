package response;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import static main.App.bot;

public class Snippet {

    private static SendMessage defaultMessage(Long chatId, String txt) {
        return new SendMessage(chatId, txt)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
    }

    public static void inputData(Long chatId) {
        bot.execute(defaultMessage(chatId, Text.inputData));
    }

    public static void help(Long chatId) {
        bot.execute(defaultMessage(chatId, Text.help)
                .replyMarkup(Keyboards.help())
        );
    }

    public static void back(Long chatId) {
        bot.execute(defaultMessage(chatId, Text.back));
    }

    public static void correctRead(Long chatId) {
        bot.execute(defaultMessage(chatId, Text.correctRead)
                .replyMarkup(Keyboards.help())
        );
    }

    public static void calcType(Long chatId) {
        bot.execute(defaultMessage(chatId, Text.calcType)
                .replyMarkup(Keyboards.calcType())
        );
    }

    public static void sectionType(Long chatId) {
        bot.execute(defaultMessage(chatId, Text.sectionType)
                .replyMarkup(Keyboards.sectionType())
        );
    }

    public static void solve(Long chatId) {
        bot.execute(defaultMessage(chatId, Text.solve));
    }

    public static class Error {

        public static void chooseSettings(Long chatId) {
            bot.execute(defaultMessage(chatId, Text.chooseSettings));
        }

        public static void undefinedCommand(Long chatId) {
            bot.execute(defaultMessage(chatId, Text.undefinedCommand));
        }

        public static void incorrectCalcType(Long chatId) {
            bot.execute(defaultMessage(chatId, Text.incorrectCalcType));
        }

        public static void incorrectSectionType(Long chatId) {
            bot.execute(defaultMessage(chatId, Text.incorrectSectionType));
        }

        public static void incorrectStepValue(Long chatId) {
            bot.execute(defaultMessage(chatId, Text.incorrectStepValue));
        }

        public static void incorrectStepQuantity(Long chatId) {
            bot.execute(defaultMessage(chatId, Text.incorrectStepQuantity));
        }

        public static void incorrectExpression(Long chatId) {
            bot.execute(defaultMessage(chatId, Text.incorrectExpression));
        }
    }
}
