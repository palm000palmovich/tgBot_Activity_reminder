package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;

@Service
public class CommandsService {
    //private final TelegramBot telegramBot;
    private Logger logger = LoggerFactory.getLogger(CommandsService.class);

   /* public CommandsService(TelegramBot telegramBot){
        this.telegramBot = telegramBot;
    }*/

    public String answerToStart(TelegramBot telegramBot, long chatId){
        String startInfo = "Привет! Я бот, напоминающий вам о важных событиях." + "\n" +
                "Вы можете добавить в меня новое событие в формате: dd.mm.yyyy hh:mm + *Текст события*";
        logger.info("Method for answer to '/start' was invoked.");
        telegramBot.execute(new SendMessage(chatId, startInfo));
        return startInfo;
    }

}
