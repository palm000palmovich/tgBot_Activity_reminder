package pro.sky.telegrambot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface TelegramCommand {
    boolean support(Update update);

    SendMessage handle(Update update);
}
