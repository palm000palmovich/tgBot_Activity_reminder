package pro.sky.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pro.sky.telegrambot.commands.TelegramCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
@EnableScheduling
public class TelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);

		String input = "12.05.2023 14:30 Some important message";
		String regex = "(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		List<String> list  = new ArrayList<>();

		if (matcher.find()) {
			String dateTimeString = matcher.group(1); // Первая группа: дата и время
			String message = matcher.group(3);

			list.add(dateTimeString);
			list.add(message);

		}

		System.out.println(list.get(0));
		System.out.println(list.get(1));

	}
}
