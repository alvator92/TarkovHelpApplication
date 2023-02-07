package ru.parsing.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.parsing.emun.TradersEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListOfQuestService {

    public static EditMessageText setCallbackMessage(long messageId, long chatId, String text, List<String> listOfQuests) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(text);
        messageText.setMessageId((int)messageId);
        messageText.setReplyMarkup(getInlineKeeBoard(listOfQuests));
        return messageText;
    }

    public static InlineKeyboardMarkup getInlineKeeBoard(List<String> listOfQuests) {
        // ответ под сообщением в виде кнопок
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        // список строк с возможными ответами
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        // одна строка один список
        listOfQuests.forEach(quest -> {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            // Добавляем в одну линию кнопкe
            rowInline.add(getButton(quest));
            rowsInLine.add(rowInline);

        });

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
