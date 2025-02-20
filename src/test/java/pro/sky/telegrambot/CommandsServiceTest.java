package pro.sky.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import pro.sky.telegrambot.services.CommandsService;

public class CommandsServiceTest {
    @Value("${telegram.bot.token}")
    private String token;
    private CommandsService commandsService = new CommandsService();
    private TelegramBot telegramBot = new TelegramBot(token);
    private final long chatId = 1713893274;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void checkStartReaction(){
        String actual =  "Привет! Я бот, напоминающий вам о важных событиях." + "\n" +
                "Вы можете добавить в меня новое событие в формате: dd.mm.yyyy hh:mm + *Текст события*";
        String expected = commandsService.answerToStart(telegramBot, chatId);
        Assertions.assertEquals(expected, actual);
    }
}
