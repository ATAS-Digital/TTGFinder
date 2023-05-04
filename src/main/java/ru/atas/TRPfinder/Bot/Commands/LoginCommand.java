package ru.atas.TRPfinder.Bot.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;
import ru.atas.TRPfinder.Records.PlayerRecord;
import ru.atas.TRPfinder.Services.PlayerService;

@Component
public class LoginCommand implements CommandInterface {

    @Autowired
    private PlayerService playerService;

    public LoginCommand(PlayerService playerService){
        this.playerService = playerService;
    }

    @Override
    public SendMessage doAction(Update update) {
        long chatId = update.getMessage().getChatId();
        String name = update.getMessage().getChat().getFirstName();
        var message = new SendMessage();
        message.setChatId(chatId);

        var playerRecord = new PlayerRecord(
                chatId,
                name);

        if (playerService.addPlayer(playerRecord)) {
            message.setText("Вы успешно зарегистрированы. Ваш никнейм: " + name);
        } else {
            message.setText("Вы уже зарегистрированы под никнеймом: " + name);
        }
        return message;
    }
}
