package ru.atas.TRPfinder.Bot.Interfaces;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandInterface {
    SendMessage doAction(Update update);
}
