package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;

@Component
public class NewGameCallback implements EventCallbackInterface {

    @Override
    public EditMessageText editMessage(Update update) {
        return null;
    }

    @Override
    public SendMessage sendMessage(Update update) {

        long chatId = update.getCallbackQuery().getMessage().getChatId();

        String answer = """
                Задайте параметры события в формате:

                Название: [название]
                Время: [ГГГГ-ММ-ДД ЧЧ:ММ]
                Часовой пояс: UTC+[часы] (например "UTC+05:00")
                Место: [адрес или ссылка]
                Описание: [любое описание]

                Обратите внимание на то, что заголовки писать не нужно""";

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        //newMessage.setMessageId(messageId);
        message.setText(answer);

        return message;
    }

    @Override
    public DeleteMessage deleteMessage(Update update) {
        return null;
    }
}
