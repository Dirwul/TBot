package main;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import structures.Info;

import java.util.List;

public class Listener {

    TelegramBot bot;

    Listener(TelegramBot bot) {
        this.bot = bot;
    }

    void chooseScript(Update update) {
        Long chatId = update.message().chat().id();
        if (!App.userInfo.containsKey(chatId)) {
            App.userInfo.put(chatId, new Info());
        }

        //debug
        Message msg = update.message();
        String txt = msg.text();
        System.out.println(msg.chat().id().toString()
                + "  ||  "
                + update.updateId().toString()
                + "  ||  cmd: "
                + txt
        );

        switch (App.userInfo.get(chatId).getState()) {
            case NONE -> new BuildScript(update).none();
            case SET_CALC_TYPE -> new BuildScript(update).settingsCalcType();
            case SET_SECTION_TYPE -> new BuildScript(update).settingsSectionType();
            case STEP_QUANTITY -> new BuildScript(update).stepQuantity();
            case STEP_VALUE ->  new BuildScript(update).stepValue();
            case FLOATING_STEP -> new BuildScript(update).floatingStep();
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
