package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;
import ru.atas.TRPfinder.Services.GameEventService;

@Component
public class NewGameCommand implements EventCallbackInterface {

    @Override
    public void HandleButton() {

    }

    @Override
    public EditMessageText getMessage(Update update/*, long messageId, long chatId*/) {
        int messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        String answer = """
                Задайте параметры события в формате:

                Название: [название]
                Время: [ГГГГ-ММ-ДД ЧЧ:ММ]
                Часовой пояс: UTC+[часы] (например "UTC+05:00")
                Место: [адрес или ссылка]
                Описание: [любое описание]

                Обратите внимание на то, что заголовки писать не нужно""";

        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(messageId);
        newMessage.setText(answer);

        return newMessage;
    }
}
