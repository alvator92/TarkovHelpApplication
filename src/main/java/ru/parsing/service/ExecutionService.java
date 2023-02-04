package ru.parsing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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


    protected void prepareAndSendMessage(long chatId, String imagePath, String imageCaption) {
        InputFile inputFile;
        try {
             inputFile = new InputFile(new FileInputStream(imagePath), imageCaption);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(inputFile);
        executeMessage(sendPhoto);

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
     * Отправление сообщения c фото
     */
    protected void executeMessage(SendPhoto sendPhoto) {
        try {
            telegramBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }

    /**
     * Отправка сообщения из кнопки
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
