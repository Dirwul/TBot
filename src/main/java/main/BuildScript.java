package main;

import com.pengrad.telegrambot.model.Update;

import static main.App.userInfo;

public class BuildScript {

    private Update update = null;

    private int[] fatherUpdateId;

    final private Long chatId = update.message().chat().id();

    BuildScript(Update update) {
        this.update = update;
    }

    void help() {
        Snippet.help(chatId);
    }

    void settings() {
        settingsCalcType();
        settingsSectionType();
    }

    void settingsCalcType() { // todo assert to undefined type
        // save and take data, snippet just request and answer
        Info input;
        while (true) {
            Snippet.calcType(chatId);
            input = SingleUpdate.getCalcType(fatherUpdateId);

            if (!input.isCalcTypeOk()) {
                Snippet.ErrorUndefinedCalcType(chatId);
                continue;
            }
            break;
        }
        userInfo.set(input);
    }

    void settingsSectionType() { // todo assert to undefined type
        Info input = new Info();
        while (true) {
            Snippet.sectionType(chatId);
            input.set(SingleUpdate.getSectionType(fatherUpdateId));
            if (!input.isSectionTypeOk()) {
                Snippet.ErrorUndefinedSecType(chatId);
                continue;
            }
            Snippet.sectionValue(chatId, input.getSectionType());
            input.set(SingleUpdate.getSectionValue(fatherUpdateId, input.getSectionType()));
            if (!input.isSectionValueOk()) {
                Snippet.ErrorUndefinedSecValue(chatId);
                continue;
            }
            break;
        }
        userInfo.set(input);
    }

    void solve() {
        if (!userInfo.isCalcTypeOk()) {
            Snippet.ErrorUndefinedCalcType(chatId);
            settingsCalcType();
        }
        if (!userInfo.isSectionTypeOk()) { // not need sectionValue
            Snippet.ErrorUndefinedSecType(chatId);
            settingsSectionType();
        }
        if (Parser.isExpressionCorrect(update.message().text())) { // todo
            Snippet.solve(chatId);
        } else {
            Snippet.ErrorIncorrectExpression(chatId);
        }
    }
}
