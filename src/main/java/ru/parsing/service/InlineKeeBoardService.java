package ru.parsing.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.parsing.common.StringConstant;

import java.util.ArrayList;
import java.util.List;

public class InlineKeeBoardService {

    /**
     * Создание кнопок под сообщением
     * @return
     */
    public static InlineKeyboardMarkup getInlineKeeBoard() {

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        // Добавляем в одну линию две кнопки
        rowInline.add(getButton("YES"));
        rowInline.add(getButton("NO"));

        rowsInLine.add(rowInline);

        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }

    private static InlineKeyboardButton getButton(String button) {
        if (button.equals("YES")) {
            var yesButton = new InlineKeyboardButton();
            yesButton.setText(StringConstant.YES);
            yesButton.setCallbackData(StringConstant.YES_BUTTON);
            return yesButton;

        } else {
            var noButton = new InlineKeyboardButton();
            noButton.setText(StringConstant.NO);
            noButton.setCallbackData(StringConstant.NO_BUTTON);
            return noButton;

        }
    }
}
