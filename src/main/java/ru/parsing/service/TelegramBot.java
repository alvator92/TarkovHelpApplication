package ru.parsing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.parsing.configuration.BotConfiguration;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfiguration botConfiguration;

    public TelegramBot(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getName();
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" :
                    startCommandRecieved(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not found");

            }
        }

    }

    private void startCommandRecieved(long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }
}
