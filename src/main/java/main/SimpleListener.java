package main;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;

import java.util.List;

import static main.App.bot;

public class SimpleListener {

    TelegramBot bot;

    SimpleListener(TelegramBot bot) {
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
        while (true) {
            Update update = bot.execute(new GetUpdates()
                            .limit(100)
                            .offset(0)
                            .timeout(0))
                    .updates()
                    .get(0);

            chooseScript(update);
        }

    }
}
