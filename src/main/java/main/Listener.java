package main;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import main.State;

import java.util.List;

public class Listener {

    TelegramBot bot;

    static State state = null;

    Listener(TelegramBot bot) {
        this.bot = bot;
    }

    void chooseScript(Update update) {
        var msg = update.message();
        var txt = msg.text();

        //debug
        System.out.println(msg.chat().id().toString()
                + "  ||  "
                + update.updateId().toString()
                + "  ||  cmd: "
                + txt
        );

        switch (state) {
            case State.NONE -> new BuildScript(update).none();
            case State.SET_CALC_TYPE -> new BuildScript(update).settingsCalcType();
            case State.SET_SECTION_TYPE -> new BuildScript(update).settingsSectionType();
            case State.SET_SECTION_VALUE -> new BuildScript(update).settingsSectionValue();
        }

        // create message
        switch (txt) {
            case "/help", "/start" -> new BuildScript(update).help();
            case "/settings" -> new BuildScript(update).settings();
            case "/set_calc" -> new BuildScript(update).settingsCalcType();
            case "/set_sec" -> new BuildScript(update).settingsSectionType();
            default -> new BuildScript(update).solve();
        };
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
