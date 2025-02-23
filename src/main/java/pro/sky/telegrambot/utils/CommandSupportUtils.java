package pro.sky.telegrambot.utils;

import com.pengrad.telegrambot.model.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandSupportUtils {

    public static List<String> getSplittedDateAndTimeFromString(Update update, String regex){
        String message = update.message().text();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        List<String> splittedDateAndTime = new ArrayList<>();

        if (matcher.find()){
            String dateTimeString = matcher.group(1);  //1st group: date and time
            String notification = matcher.group(3);  //3rd group: notification

            //List.get(0) - date and time
            //List.get(1) - notification
            splittedDateAndTime.add(dateTimeString);
            splittedDateAndTime.add(notification);
        }

        return splittedDateAndTime;
    }

    public static boolean ifStringLooksLikeRegex(Update update, String regex){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.text())
                .map(it -> it.matches(regex))
                .orElse(false);
    }

    public static boolean ifStringEqualsCommand(Update update, String command) {
        return  text(update)
                .map(it -> command.equals(it))
                .orElse(false);
    }

    public static Optional<String> text(Update update){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.text());
    }

    public static Long chatId(Update update){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.chat())
                .map(it -> it.id())
                .orElse(-1L);
    }
}
