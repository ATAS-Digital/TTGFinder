package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Services.EventRegistrationService;
import ru.atas.TRPfinder.Services.GameEventService;
import ru.atas.TRPfinder.Services.PlayerService;

@Component
public class RegisterToGameCallback implements EventCallbackInterface {

    private final PlayerService playerService;
    private final EventRegistrationService eventRegistrationService;

    private final GameEventService gameEventService;

    public RegisterToGameCallback(PlayerService playerService,
                                  EventRegistrationService eventRegistrationService,
                                  GameEventService gameEventService){
        this.playerService = playerService;
        this.eventRegistrationService = eventRegistrationService;
        this.gameEventService = gameEventService;
    }

    @Override
    public EditMessageText editMessage(Update update) {
        return null;
    }

    @Override
    public AnswerCallbackQuery answerCallback(Update update){
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(update.getCallbackQuery().getId());
        answer.setShowAlert(true);

        long chatId = update.getCallbackQuery().getMessage().getChatId();
        long gameID = Long.parseLong(update.getCallbackQuery().getData().substring(7));

        EventRegistration eventRegistration = new EventRegistration(
                chatId,
                gameID,
                "player"
        );

        if (playerService.getPlayerById(chatId) == null){
            answer.setText("Сначала зарегистрируйтесь, прежде чем записываться на игры");
        }
        // проверка, зарегистрирован ли пользователь уже на это событие
        else if (eventRegistrationService.getRegistrationsOnGame(gameID).stream().anyMatch(
                c -> c.getPlayerId().equals(chatId)))
        {
            if (gameEventService.getMasterName(gameID).equals(
                    update.getCallbackQuery().getMessage().getChat().getUserName())){
                answer.setText(String.format("Вы уже зарегистрированы на игру \"%s\" в роли мастера",
                        gameEventService.getGameById(gameID).getName()));
            }
            else answer.setText(String.format("Вы уже зарегистрированы на игру \"%s\" в роли игрока",
                    gameEventService.getGameById(gameID).getName()));
        }
        else {
            eventRegistrationService.addNewRegistration(eventRegistration);
            answer.setText(String.format("Вы успешно зарегистрированы на игру \"%s\"!",
                    gameEventService.getGameById(gameID).getName()));
        }

        return answer;
    }

    @Override
    public SendMessage sendMessage(Update update) {
        return null;
    }

    @Override
    public DeleteMessage deleteMessage(Update update) {
        return null;
    }
}
