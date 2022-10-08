package main;

import com.pengrad.telegrambot.model.Update;
import languageRecognition.Validator;
import response.Snippet;
import structures.CalculationType;
import structures.SectionType;
import structures.State;

public class BuildScript {

    private final String txt;

    private final Long chatId;

    BuildScript(Update update) {
        final String botName = "@Dirwul_bot";
        int botLen = botName.length();

        String tmpTxt = update.message().text();

        if (tmpTxt.length() > botLen && tmpTxt.startsWith("@Dirwul_bot")) {
            tmpTxt = tmpTxt.substring(botLen + 1);
        }

        this.txt = tmpTxt.replaceAll(" ", "");
        this.chatId = update.message().chat().id();
    }

    public void none() {
        switch (txt.toLowerCase()) { // maybe snippets need chatId to return
            case "/back" -> {
                Snippet.back(chatId);
                Listener.state = State.NONE;
            }
            case "/help", "/start" -> Snippet.help(chatId);
            case "/calculationtype" -> {
                Snippet.calcType(chatId);
                Listener.state = State.SET_CALC_TYPE;
            }
            case "/sectiontype" -> {
                Snippet.sectionType(chatId);
                Listener.state = State.SET_SECTION_TYPE;
            }
            case "/solve" -> {
                if (App.userInfo.isOk()) { // todo
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

        boolean isOk = false;
        switch (txt.toLowerCase()) {
            case "/help", "/back" -> {
                Snippet.help(chatId);
                Listener.state = State.NONE;
            }
            case "/leftrectangle" -> {
                App.userInfo.setCalcType(CalculationType.LEFT_RECTANGLE);
                isOk = true;
            }
            case "/rightrectangle" -> {
                App.userInfo.setCalcType(CalculationType.RIGHT_RECTANGLE);
                isOk = true;
            }
            case "/trapezoid" -> {
                App.userInfo.setCalcType(CalculationType.TRAPEZOID);
                isOk = true;
            }
            case "/parabolic" -> {
                App.userInfo.setCalcType(CalculationType.PARABOLA);
                isOk = true;
            }
            default -> {
                Snippet.Error.incorrectCalcType(chatId);
                Listener.state = State.SET_CALC_TYPE;
            }
        }
        if (isOk) {
            Snippet.correctRead(chatId);
        }
    }

    public void settingsSectionType() {
        boolean isOk = false;
        switch (txt.toLowerCase()) {
            case "/help", "/back" -> {
                Snippet.help(chatId);
                Listener.state = State.NONE;
            }
            case "/stepvalue" -> {
                App.userInfo.setSectionType(SectionType.BY_STEP_VALUE);
                Listener.state = State.STEP_VALUE;
                isOk = true;
            }
            case "/stepquantity" -> {
                App.userInfo.setSectionType(SectionType.BY_STEP_QUANTITY);
                Listener.state = State.STEP_QUANTITY;
                isOk = true;
            }
            default -> Snippet.Error.incorrectSectionType(chatId);
        }
        if (isOk) {
            Snippet.inputData(chatId);
        }
    }

    public void stepValue() {
        if (txt.equalsIgnoreCase("/help") ||
                txt.equalsIgnoreCase("/back")
        ) {
            Snippet.help(chatId);
            Listener.state = State.NONE;
        } else if (Validator.isCorrectDouble(txt)) {
            App.userInfo.setStepValue(Double.parseDouble(txt));
            Listener.state = State.NONE;
            Snippet.correctRead(chatId);
        } else {
            Snippet.Error.incorrectStepValue(chatId);
        }
    }

    public void stepQuantity() {
        if (txt.equalsIgnoreCase("/help") ||
                txt.equalsIgnoreCase("/back")
        ) {
            Snippet.help(chatId);
            Listener.state = State.NONE;
        } else if (Validator.isCorrectInteger(txt)) {
            App.userInfo.setStepQuantity(Integer.parseInt(txt));
            Listener.state = State.NONE;
            Snippet.correctRead(chatId);
        } else {
            Snippet.Error.incorrectStepQuantity(chatId);
        }
    }

    public void solve() {
        switch (txt.toLowerCase()) {
            case "/back" -> {
                Snippet.back(chatId);
                Listener.state = State.NONE;
            }
            case "/help", "/start" -> Snippet.help(chatId);
            case "/calculationtype" -> {
                Snippet.calcType(chatId);
                Listener.state = State.SET_CALC_TYPE;
            }
            case "/sectiontype" -> {
                Snippet.sectionType(chatId);
                Listener.state = State.SET_SECTION_TYPE;
            }
            case "/solve" -> Snippet.solve(chatId);
            default -> {
                if (Validator.isCorrectExpression(txt)) {
                    Snippet.getSolvingResult(chatId, App.userInfo, txt);
                } else {
                    Snippet.Error.incorrectExpression(chatId); // todo not only expression error
                }
            }
        }
    }
}
