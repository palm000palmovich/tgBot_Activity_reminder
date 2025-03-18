package pro.sky.telegrambot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.utils.CommandSupportUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pro.sky.telegrambot.utils.CommandSupportUtils.text;

@Component
public class NotificationCommand implements TelegramCommand{

    private final Pattern regexPattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");


    private final String REGEX = "(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)";
    private Logger logger = LoggerFactory.getLogger(NotificationCommand.class);

    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationCommand(NotificationTaskRepository notificationTaskRepository){
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Override
    public boolean support(Update update) {
        Optional<String>  text  = text(update);
        return text
                .map(it -> it.matches(regexPattern.pattern()))
                .orElse(false);
    }

    @Override
    public SendMessage handle(Update update) {
        logger.info("Method for answer to creating notification was invoked.");
        String user = update.message().chat().username();
        String chatId = CommandSupportUtils.chatId(update).toString();

        /*List<String> dateAndTimeList = CommandSupportUtils.getSplittedDateAndTimeFromString(update, REGEX);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateAndTimeList.get(0), formatter);


        //DML + testing of saving new notifications
        List<NotificationTask> beforeSavinglist = notificationTaskRepository.findAll();
        NotificationTask notificationTask = new NotificationTask(dateTime, dateAndTimeList.get(1), chatId);
        notificationTaskRepository.save(notificationTask);
        List<NotificationTask> afterSavingList = notificationTaskRepository.findAll();
        if (afterSavingList.size() > beforeSavinglist.size()){logger.debug("Saving was successful: " +
                notificationTask.toString());} else {logger.debug("Saving failed");}


        String notifInfo = "Напоминание " + dateAndTimeList.get(0) + " " + dateAndTimeList.get(1) +
                " успешно добавлено!";
        String format = String.format(notifInfo, user);
        logger.debug("Message sent to " + chatId);
        //return new SendMessage(String.valueOf(chatId), format);*/



        Optional<String>  text = text(update);
        if (text.isPresent()){
            Matcher matcher = regexPattern.matcher(text.get());

            if (matcher.find()){
                String date = matcher.group(1);
                String notification = matcher.group(3);
                NotificationTask notificationTask1 = new NotificationTask(
                        LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                        notification,
                        chatId);
                notificationTaskRepository.save(notificationTask1);

                return new SendMessage(chatId, "Notification added successfully! " + date);
            }

        }

        return new SendMessage(chatId, "Sorry, saving of notification is failed(");

    }

}
