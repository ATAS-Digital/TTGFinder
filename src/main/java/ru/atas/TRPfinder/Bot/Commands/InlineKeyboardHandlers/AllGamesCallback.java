package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;
import ru.atas.TRPfinder.Entities.GameEvent;
import ru.atas.TRPfinder.Services.GameEventService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AllGamesCallback implements EventCallbackInterface {

    private int size;
    private int iterations;
    private int additionalIterations;
    List<GameEvent> gamesList;

    private final GameEventService gameEventService;

    public AllGamesCallback(GameEventService gameEventService){
        this.gameEventService = gameEventService;
    }

    @Override
    public EditMessageText getMessage(Update update) {
        int size = gamesList.size();
        iterations = (size / 5) - 1;
        additionalIterations = size % 5;

        return null;
    }

    // сделать вывод по 5 и менее пунктов вместе с клавиатурой,
    // через которую можно провалится ниже,
    // чтобы посмотреть инфу об игре и записаться, если нужно (со возможностью вернутся к списку)
    @Override
    public SendMessage sendMessage(Update update) {
        size = gameEventService.getAllGames().size();
        iterations = size / 5;
        additionalIterations = size % 5;
        gamesList = gameEventService.getAllGames();

        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId());

        StringBuilder builder = new StringBuilder();



        if (size >= 5) {
            for(int i = 0; i < 5; i++){
                builder.append(String.format("""
                    %s
                    [%s]
                    %s\n
                    """, gamesList.get(i).getName(), gamesList.get(i).getDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")),
                        gameEventService.getMasterName(gamesList.get(i).getId())));
            }
            message.setText(builder.toString());
        }
        else{
            for(int i = 0; i < size; i++){
                builder.append(String.format("""
                    %s
                    [ %s ]
                    Мастер: @%s\n
                    """, gamesList.get(i).getName(), gamesList.get(i).getDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")),
                        gameEventService.getMasterName(gamesList.get(i).getId())));
            }
            message.setText(builder.toString());
        }


        return message;
    }
}
