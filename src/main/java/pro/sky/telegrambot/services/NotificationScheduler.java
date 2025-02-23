package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationScheduler {

    private final NotificationTaskRepository notificationTaskRepository;
    private final TelegramBot telegramBot;

    public NotificationScheduler(NotificationTaskRepository notificationTaskRepository, TelegramBot telegramBot) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(fixedDelay = 15000)
    public void sendNotifications(){
        List<NotificationTask> tasks =  notificationTaskRepository.findAllByDateTimeBefore(LocalDateTime.now());

        tasks
                .forEach(notificationTask -> {
                    telegramBot.execute(new SendMessage(notificationTask.getChatId(), notificationTask.getText()));
                    notificationTaskRepository.delete(notificationTask);
                });
    }
}
