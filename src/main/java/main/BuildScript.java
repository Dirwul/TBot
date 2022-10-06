package main;

import solve.Parser;
import com.pengrad.telegrambot.model.Update;
import response.Snippet;
import structures.CalculationType;
import structures.Info;
import structures.SectionType;
import structures.State;

public class BuildScript {

    private final String txt;

    private final Long chatId;

    BuildScript(Update update) {
        this.txt = update.message().text();
        this.chatId = update.message().chat().id();
    }

    public void none() {
        switch (txt.toLowerCase()) { // maybe snippets need chatId to return
            case "/help", "/start" -> Snippet.help(chatId);
            case "/set_calc" -> {
                Snippet.calcType(chatId);
                Listener.state = State.SET_CALC_TYPE;
            }
            case "/set_sec" -> {
                Snippet.sectionType(chatId);
                Listener.state = State.SET_SECTION_TYPE;
            }
            case "/solve" -> {
                if (App.userInfo.isOk()) {
                    Snippet.solve(chatId);
                    Listener.state = State.SOLVE;
                } else {
                    Snippet.Error.chooseSettings(chatId);
                }
            }
            default -> Snippet.Error.undefinedCommand(chatId);
        }
    }

    public void settingsCalcType() {
        Listener.state = State.NONE;

        switch (txt.toLowerCase()) {
            case "leftrectangle" -> App.userInfo.setCalcType(CalculationType.LEFT_RECTANGLE);
            case "rightrectangle" -> App.userInfo.setCalcType(CalculationType.RIGHT_RECTANGLE);
            case "trapezoid", "/trapezoid" -> App.userInfo.setCalcType(CalculationType.TRAPEZOID);
            case "parabolic", "/parabolic" -> App.userInfo.setCalcType(CalculationType.PARABOLA);
            default -> {
                Snippet.Error.incorrectCalcType(chatId);
                Listener.state = State.SET_CALC_TYPE;
            }
        }
    }

    public void settingsSectionType() {
        switch (txt.toLowerCase()) {
            case "by_value" -> {
                App.userInfo.setSectionType(SectionType.BY_STEP_VALUE);
                Listener.state = State.STEP_VALUE;
            }
            case "by_quantity" -> {
                App.userInfo.setSectionType(SectionType.BY_STEP_QUANTITY);
                Listener.state = State.STEP_QUANTITY;
            }
            default -> Snippet.Error.incorrectSectionType(chatId);
        }
    }

    public void stepValue() {
        if (Parser.isDouble(txt) && Double.parseDouble(txt) > Info.EPS) {
            App.userInfo.setStepValue(Double.parseDouble(txt));
            Listener.state = State.NONE;
            return;
        }

        Snippet.Error.incorrectStepValue(chatId);
    }

    public void stepQuantity() {
        if (Parser.isInteger(txt) && Integer.parseInt(txt) > 0) {
            App.userInfo.setStepQuantity(Integer.parseInt(txt));
            Listener.state = State.NONE;
            return;
        }

        Snippet.Error.incorrectStepQuantity(chatId);
    }

    public void solve() {
        if (App.userInfo.isOk() && Parser.isExpressionCorrect(txt)) {
            Listener.state = State.NONE;
            Snippet.solve(chatId);
            return;
        }

        Snippet.Error.incorrectExpression(chatId); // todo not only expression error
    }
}
