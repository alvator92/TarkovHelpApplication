package ru.parsing.service;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.parsing.emun.TradersEnum;

import java.util.ArrayList;
import java.util.List;

public class TradersService {

    public static EditMessageText setCallbackMessage(long messageId, long chatId, String text) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(text);
        messageText.setMessageId((int)messageId);
        messageText.setReplyMarkup(getInlineKeeBoard());
        return messageText;
    }

    public static InlineKeyboardMarkup getInlineKeeBoard() {
        // ответ под сообщением в виде кнопок
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        // список строк с возможными ответами
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        // одна строка один список
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        // Добавляем в одну линию три кнопки
        rowInline.add(getButton(TradersEnum.PRAPOR.getUserName()));
        rowInline.add(getButton(TradersEnum.THERAPIST.getUserName()));
        rowInline.add(getButton(TradersEnum.SKIER.getUserName()));
        rowsInLine.add(rowInline);

        List<InlineKeyboardButton> rowInSecondline = new ArrayList<>();
        rowInSecondline.add(getButton(TradersEnum.PEACEMAKER.getUserName()));
        rowInSecondline.add(getButton(TradersEnum.RAGMAN.getUserName()));
        rowsInLine.add(rowInSecondline);

        List<InlineKeyboardButton> rowInThirdline = new ArrayList<>();
        rowInThirdline.add(getButton(TradersEnum.MECHANIC.getUserName()));
        rowInThirdline.add(getButton(TradersEnum.YAEGER.getUserName()));
        rowInThirdline.add(getButton(TradersEnum.FENCE.getUserName()));
        rowsInLine.add(rowInThirdline);

        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }

    private static InlineKeyboardButton getButton(String response) {
        var button = new InlineKeyboardButton();
        button.setText(response);
        button.setCallbackData(response);
        return button;

    }

}
