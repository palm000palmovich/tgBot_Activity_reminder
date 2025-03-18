package pro.sky.telegrambot.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.utils.CommandSupportUtils;

import java.util.Optional;

@Component
public class StartCommand implements TelegramCommand{

    private Logger logger = LoggerFactory.getLogger(StartCommand.class);

    private static final String START = "/start";

    @Override
    public boolean support(Update update) {
        return CommandSupportUtils.ifStringEqualsCommand(update, START);
    }

    @Override
    public SendMessage handle(Update update) {
        String user = update.message().chat().username();
        long chatId = CommandSupportUtils.chatId(update);

        String startInfo = "Привет! Я бот, напоминающий вам о важных событиях.";
        logger.info("Method for answer to '/start' was invoked.");

        String format = String.format(startInfo, user);
        return new SendMessage(String.valueOf(chatId), format);
    }

}
