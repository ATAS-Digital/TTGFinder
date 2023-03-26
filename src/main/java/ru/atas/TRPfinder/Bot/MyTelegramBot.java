package ru.atas.TRPfinder.Bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
// We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            //message.setText(update.getMessage().getText());

            StringBuilder sb = new StringBuilder(update.getMessage().getText());

            System.out.println(sb.toString());
            //message.setText("text");

//            try {
//                execute(message); // Call method to send the message
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public String getBotToken() {
        return "123";
    }

    @Override
    public String getBotUsername() {
        return "123";
    }
}
