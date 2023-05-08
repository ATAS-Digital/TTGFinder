package ru.atas.TRPfinder.Bot.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;
import ru.atas.TRPfinder.Services.PlayerService;

import java.util.NoSuchElementException;

@Component
public class ProfileCommand implements CommandInterface {

    @Autowired
    private PlayerService playerService;

    public ProfileCommand(PlayerService playerService){
        this.playerService = playerService;
    }

    @Override
    public SendMessage doAction(Update update) {
        String text;
        long chatId = update.getMessage().getChatId();
        try {
            var player = playerService.getPlayerById(chatId);
            text = "Ваши данные:" + "\n\n" +
                    "name: " + player.getName() + "\n" +
                    "id: " + player.getId();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR!! Запись не найдена в БД: " + e.getMessage());
            text = "Вы ещё не зарегистрировались :)";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }
}
