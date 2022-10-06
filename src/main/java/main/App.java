package main;


import com.pengrad.telegrambot.TelegramBot;

public class App {

    static TelegramBot bot;

    static Info userInfo;

    private void run() {
        // connect bot
        bot = new TelegramBot("5748684751:AAElE9xbxjKQHeOnNvekHyjRRauJorBH0sQ");

        // bot settings
        // todo

        // run listener
        new Listener(bot).run();
    }

    void init() {
        try {
            run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new main.App().init();
    }
}
