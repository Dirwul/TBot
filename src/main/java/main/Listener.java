package main;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import structures.State;

import java.util.List;

public class Listener {

    TelegramBot bot;

    static State state = State.NONE;

    Listener(TelegramBot bot) {
        this.bot = bot;
    }

    void chooseScript(Update update) {
        Message msg = update.message();
        String txt = msg.text();
        //debug
        System.out.println(msg.chat().id().toString()
                + "  ||  "
                + update.updateId().toString()
                + "  ||  cmd: "
                + txt
        );

        switch (state) {
            case NONE -> new BuildScript(update).none();
            case SET_CALC_TYPE -> new BuildScript(update).settingsCalcType();
            case SET_SECTION_TYPE -> new BuildScript(update).settingsSectionType();
            case STEP_QUANTITY -> new BuildScript(update).stepQuantity();
            case STEP_VALUE ->  new BuildScript(update).stepValue();
            case SOLVE -> new BuildScript(update).solve();
            default -> System.out.println("Smth gets wrong in state choosing");
        }
    }

    void run() {
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                for (var update : updates) {
                    chooseScript(update);
                }
                return UpdatesListener.CONFIRMED_UPDATES_ALL; // maybe must return no_update
            }
        });
    }
}
