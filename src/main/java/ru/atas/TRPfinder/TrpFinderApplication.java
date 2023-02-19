package ru.atas.TRPfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.atas.TRPfinder.Bot.MyTelegramBot;
import ru.atas.TRPfinder.Services.PlayerService;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class TrpFinderApplication {

	public static void main(String[] args) {
//		try {
//			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//			botsApi.registerBot(new MyTelegramBot());
//		} catch (TelegramApiException e) {
//			e.printStackTrace();}
		SpringApplication.run(TrpFinderApplication.class, args);
	}

}
