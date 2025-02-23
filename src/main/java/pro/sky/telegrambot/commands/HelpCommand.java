package pro.sky.telegrambot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.telegrambot.utils.CommandSupportUtils;

public class HelpCommand implements TelegramCommand{
    private static final String HELP = "/help";
    private Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public boolean support(Update update) {
        return CommandSupportUtils.ifStringEqualsCommand(update, HELP);
    }

    @Override
    public SendMessage handle(Update update) {
        logger.info("Method for answer to '/help' was invoked.");

        String user = update.message().chat().username();
        long chatId = CommandSupportUtils.chatId(update);

        String helpInfo = "Вы можете добавить в меня новое событие в формате: dd.mm.yyyy hh:mm + *Текст напоминания*," +
                " и я в момент наступления указанной даты вам о нем напомню.";

        String format = String.format(helpInfo, user);
        logger.debug("Message sent to " + chatId);
        return new SendMessage(String.valueOf(chatId), format);
    }
}