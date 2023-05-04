package ru.atas.TRPfinder.Bot.Commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;
import ru.atas.TRPfinder.Records.GameEventRecord;
import ru.atas.TRPfinder.Services.GameEventService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeData implements CommandInterface {

    private final GameEventService gameEventService;
    private GameEventRecord gameEventRecord;

    public TimeData(GameEventService gameEventService){
        this.gameEventService = gameEventService;
    }


    @Override
    public SendMessage doAction(Update update) {
        String name;
        ZonedDateTime time;
        String timeZone;
        String place;
        String description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

        int[] startIndexesInLines = new int[]{0, 0, 4, 0, 0};
        var updateMsg = update.getMessage().getText().split("\n");

        name = updateMsg[0].substring(startIndexesInLines[0]);
        String tempTime = updateMsg[1].substring(startIndexesInLines[1]);//.replace(' ', 'T');
        timeZone = updateMsg[2].substring(startIndexesInLines[2]);
        tempTime += " +" + timeZone;
        time = ZonedDateTime.parse(tempTime, formatter);

        place = updateMsg[3].substring(startIndexesInLines[3]);
        description = updateMsg[4].substring(startIndexesInLines[4]);

        gameEventRecord = new GameEventRecord(time, name, place, description);

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());

        if(gameEventService.addNewGame(gameEventRecord))
            message.setText("Ваша игра успешно добавлена!");
        else
            message.setText("Игра с таким названием уже существует. Пожалуйста, выберете другое название");
        return message;
    }
}
