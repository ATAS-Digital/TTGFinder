package ru.atas.TRPfinder.Bot.Commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;

@Component
public class DefaultAnswer implements CommandInterface {

    @Override
    public SendMessage doAction(Update update) {
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Извините, я не знаю такой команды. :(\n\nПопробуйте написать команду из доступных.");
        return message;
    }
}
