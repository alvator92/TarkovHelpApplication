package ru.parsing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class ExecutionService {

    @Autowired
    private TelegramBot telegramBot;

    /**
     * Подготовка сообщения к отправлению
     */
    protected void prepareAndSendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        executeMessage(message);
    }
    /**
     * Подготовка стартового сообщения с клавиатурой к отправлению
     */
    protected void prepareAndSendStartMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        message.setReplyMarkup(KeeBoardService.getKeeBoard());
        executeMessage(message);
    }

    /**
     * Отправление сообщения
     * @param message
     */
    protected void executeMessage(SendMessage message) {
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }

    /**
     * Отправка сообщения из кнопки
     * @param message
     */
    protected void executionEditMessage(EditMessageText message) {
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }
}
