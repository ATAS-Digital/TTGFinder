package ru.atas.TRPfinder.Bot.Interfaces;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface EventCallbackInterface {
    void HandleButton();
    EditMessageText getMessage(Update update/*, long messageId, long chatId*/);
}
