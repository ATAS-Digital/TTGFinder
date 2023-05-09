package ru.atas.TRPfinder.Bot.Commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Records.GameEventRecord;
import ru.atas.TRPfinder.Services.EventRegistrationService;
import ru.atas.TRPfinder.Services.GameEventService;
import ru.atas.TRPfinder.Services.PlayerService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeData implements CommandInterface {

    private final GameEventService gameEventService;
    private final EventRegistrationService eventRegistrationService;
    private final PlayerService playerService;

    public TimeData(GameEventService gameEventService, EventRegistrationService eventRegistrationService, PlayerService playerService){
        this.gameEventService = gameEventService;
        this.eventRegistrationService = eventRegistrationService;
        this.playerService = playerService;
    }


    @Override
    public SendMessage doAction(Update update) {
        var chatID = update.getMessage().getChatId();

        String name;
        ZonedDateTime time;
        String timeZone;
        String place;
        String description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());

        var updateMsg = update.getMessage().getText().split("\n");

            try {
                name = updateMsg[0];
                String tempTime = updateMsg[1];//.replace(' ', 'T');
                timeZone = updateMsg[2].substring(4);
                tempTime += " +" + timeZone;
                time = ZonedDateTime.parse(tempTime, formatter);
                place = updateMsg[3];
                description = updateMsg[4];
                GameEventRecord gameEventRecord = new GameEventRecord(time, name, place, description);

                if (playerService.getPlayers().stream().noneMatch(x -> chatID.equals(x.getId()))) {
                    message.setText("Сначала зарегистрируйтесь. Вы не можете создать игру без регистрации");
                }
                else if (gameEventService.addNewGame(gameEventRecord)) {
                    message.setText("Ваша игра успешно добавлена!");
                    EventRegistration eventRegistration = new EventRegistration(
                            chatID,
                            //gameEventService.getGameById((long) gameEventService.getAllGames().size() - 1L).getId() + 1,
                            gameEventService.getGameByName(gameEventRecord.name()).getId(),
                            "master"
                    );
                    eventRegistrationService.addNewRegistration(eventRegistration);
                }
                else
                    message.setText("Игра с таким названием уже существует. Пожалуйста, выберете другое название");
            }
            catch (Exception e) {
                message.setText("Что-то указано неверно. Попробуйте написать ещё раз.");
            }

        return message;
    }
}
