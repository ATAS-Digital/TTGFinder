package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
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
    public SendMessage sendMessage(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId());

        long gameID = Long.parseLong(update.getCallbackQuery().getData().substring(7));

        EventRegistration eventRegistration = new EventRegistration(
                chatId,
                gameID,
                "player"
        );

        if (playerService.getPlayerById(chatId) == null){
            message.setText("Сначала зарегистрируйтесь, прежде чем записываться на игры");
        }
        // проверка, зарегистрирован ли пользователь уже на это событие
        else if (eventRegistrationService.getRegistrationsOnGame(gameID).stream().anyMatch(
                c -> c.getPlayerId().equals(chatId)))
        {
            if (gameEventService.getMasterName(gameID).equals(
                    update.getCallbackQuery().getMessage().getChat().getUserName())){
                message.setText("Вы уже зарегистрированы на эту игру в роли мастера");
            }
            else message.setText("Вы уже зарегистрированы на эту игру в роли игрока");
        }
        else {
            eventRegistrationService.addNewRegistration(eventRegistration);
            message.setText("Вы успешно зарегистрированы на эту игру!");
        }

        return message;
    }

    @Override
    public DeleteMessage deleteMessage(Update update) {
        return null;
    }
}
