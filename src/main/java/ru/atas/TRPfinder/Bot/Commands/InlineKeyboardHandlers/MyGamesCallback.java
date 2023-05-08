package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Services.EventRegistrationService;
import ru.atas.TRPfinder.Services.GameEventService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyGamesCallback implements EventCallbackInterface {

    private final EventRegistrationService eventRegistrationService;
    private final GameEventService gameEventService;

    public MyGamesCallback(EventRegistrationService eventRegistrationService, GameEventService gameventService){
        this.eventRegistrationService = eventRegistrationService;
        this.gameEventService = gameventService;
    }

    @Override
    public EditMessageText editMessage(Update update) {
        return null;
    }

    @Override
    public SendMessage sendMessage(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.enableMarkdownV2(true);

        List<EventRegistration> playerGamesList = eventRegistrationService.getAll().stream()
                .filter(p -> p.getPlayerId().equals(chatId))
                .toList();

        StringBuilder builder = new StringBuilder();

        var keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        long gameID;
        for (EventRegistration eventRegistration : playerGamesList) {
            gameID = eventRegistration.getGameId();
            builder.append(String.format("""
                            "*%s*"
                            **Дата и время:**
                            [ %s ]
                            **Описание:**
                            `%s`
                            **Роль:** %s
                            **Контакт мастера:** @%s
                            
                            """,
                    gameEventService.getGameById(gameID).getName(),
                    gameEventService.getGameById(gameID).getDate()
                            .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                    gameEventService.getGameById(gameID).getDescription(),
                    eventRegistration.getRole().toString(),
                    gameEventService.getMasterName(gameID)));
        }

        var str = builder.toString().replace("!", "\\!")
                .replace("@", "\\@")
                .replace("_", "\\_")
                .replace("-", "\\-")
                .replace("=", "\\=")
                .replace("&", "\\&");

        message.setText(str);

        var closeButton = new InlineKeyboardButton();
        closeButton.setText("[X] закрыть");
        closeButton.setCallbackData("close");
        List<InlineKeyboardButton> secondLine = new ArrayList<>();
        secondLine.add(closeButton);
        rowsInLine.add(secondLine);

        keyboard.setKeyboard(rowsInLine);
        message.setReplyMarkup(keyboard);

        return message;
    }

    @Override
    public AnswerCallbackQuery answerCallback(Update update) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(update.getCallbackQuery().getId());
        return answer;
    }

    @Override
    public DeleteMessage deleteMessage(Update update) {
        return null;
    }
}
