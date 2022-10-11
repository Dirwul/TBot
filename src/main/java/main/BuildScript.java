package main;

import com.pengrad.telegrambot.model.Update;
import languageRecognition.Validator;
import response.Snippet;
import structures.CalculationType;
import structures.SectionType;
import structures.State;
import structures.Info;

public class BuildScript {

    private final String txt;

    private final Long chatId;

    private final Info user;

    BuildScript(Update update) {
        final String botName = "@Dirwul_bot";
        int botLen = botName.length();

        String tmpTxt = update.message().text();

        if (tmpTxt.length() > botLen && tmpTxt.startsWith("@Dirwul_bot")) {
            tmpTxt = tmpTxt.substring(botLen + 1);
        }

        this.txt = tmpTxt.replaceAll(" ", "");
        this.chatId = update.message().chat().id();
        this.user = App.userInfo.get(chatId);
    }

    public void none() {
        switch (txt.toLowerCase()) { // maybe snippets need chatId to return
            case "/back" -> {
                Snippet.back(chatId);
                user.setState(State.NONE);
            }
            case "/help", "/start" -> Snippet.help(chatId);
            case "/calculationtype" -> {
                Snippet.calcType(chatId);
                user.setState(State.SET_CALC_TYPE);
            }
            case "/sectiontype" -> {
                Snippet.sectionType(chatId);
                user.setState(State.SET_SECTION_TYPE);
            }
            case "/solve" -> {
                if (App.userInfo.get(chatId).isOk()) { // todo
                    Snippet.solve(chatId);
                    user.setState(State.SOLVE);
                } else {
                    Snippet.Error.chooseSettings(chatId);
                }
            }
            default -> Snippet.Error.undefinedCommand(chatId);
        }
    }

    public void settingsCalcType() {
        user.setState(State.NONE);

        boolean isOk = false;
        switch (txt.toLowerCase()) {
            case "/help", "/back" -> {
                Snippet.help(chatId);
                user.setState(State.NONE);
            }
            case "/leftrectangle" -> {
                user.setCalcType(CalculationType.LEFT_RECTANGLE);
                isOk = true;
            }
            case "/rightrectangle" -> {
                user.setCalcType(CalculationType.RIGHT_RECTANGLE);
                isOk = true;
            }
            case "/trapezoid" -> {
                user.setCalcType(CalculationType.TRAPEZOID);
                isOk = true;
            }
            case "/parabolic" -> {
                user.setCalcType(CalculationType.PARABOLA);
                isOk = true;
            }
            default -> {
                Snippet.Error.incorrectCalcType(chatId);
                user.setState(State.SET_CALC_TYPE);
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
                user.setState(State.NONE);
            }
            case "/stepvalue" -> {
                user.setSectionType(SectionType.BY_STEP_VALUE);
                user.setState(State.STEP_VALUE);
                isOk = true;
            }
            case "/stepquantity" -> {
                user.setSectionType(SectionType.BY_STEP_QUANTITY);
                user.setState(State.STEP_QUANTITY);
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
            user.setState(State.NONE);
        } else if (Validator.isCorrectDouble(txt)) {
            user.setStepValue(Double.parseDouble(txt));
            user.setState(State.NONE);
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
            user.setState(State.NONE);
        } else if (Validator.isCorrectInteger(txt)) {
            user.setStepQuantity(Integer.parseInt(txt));
            user.setState(State.NONE);
            Snippet.correctRead(chatId);
        } else {
            Snippet.Error.incorrectStepQuantity(chatId);
        }
    }

    public void solve() {
        switch (txt.toLowerCase()) {
            case "/back" -> {
                Snippet.back(chatId);
                user.setState(State.NONE);
            }
            case "/help", "/start" -> Snippet.help(chatId);
            case "/calculationtype" -> {
                Snippet.calcType(chatId);
                user.setState(State.SET_CALC_TYPE);
            }
            case "/sectiontype" -> {
                Snippet.sectionType(chatId);
                user.setState(State.SET_SECTION_TYPE);
            }
            case "/solve" -> Snippet.solve(chatId);
            default -> {
                if (Validator.isCorrectExpression(txt)) {
                    Snippet.getSolvingResult(chatId, App.userInfo.get(chatId), txt);
                } else {
                    Snippet.Error.incorrectExpression(chatId); // todo not only expression error
                }
            }
        }
    }
}
