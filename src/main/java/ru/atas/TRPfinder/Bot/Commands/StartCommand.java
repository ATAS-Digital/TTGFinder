package ru.atas.TRPfinder.Bot.Commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;

@Component
public class StartCommand implements CommandInterface {

    @Override
    public SendMessage doAction(Update update) {
        long chatId = update.getMessage().getChatId();
        String name = update.getMessage().getChat().getFirstName();
        String answer = "Привет, " + name + "! Рады с тобой познакомится!!\n\n" +
                "Этот бот позволит тебе:\n" +
                "1) найти партию для игры в НРИ, если ты игрок\n" +
                "2) найти игроков себе на игру, если ты мастер ;)\n\n" +
                "Регистрируйся через /login, пиши команду /events и исследуй возможности бота!";

        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);
        return message;
    }
}
