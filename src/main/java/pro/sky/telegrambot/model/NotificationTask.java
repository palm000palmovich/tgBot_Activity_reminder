package pro.sky.telegrambot.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notifications_task")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "task", nullable = false)
    private String task;

    @Column(name = "chat_id")
    private String chatId;

    public NotificationTask() {
    }

    public NotificationTask(LocalDateTime dateTime, String task, String chatId) {
        this.dateTime = dateTime;
        this.task = task;
        this.chatId = chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", text='" + task + '\'' +
                ", chatId=" + chatId +
                '}';
    }
}
