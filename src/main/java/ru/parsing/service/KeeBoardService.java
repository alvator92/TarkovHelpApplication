package ru.parsing.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.parsing.emun.TradersEnum;

import java.util.ArrayList;
import java.util.List;

public class KeeBoardService {

    /**
     * Создание кнопок в главном меню
     */
    public static ReplyKeyboardMarkup getKeeBoard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(getKeyBoardRows());
        return keyboardMarkup;

    }
    private static List<KeyboardRow> getKeyBoardRows() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        keyboardRows.add(getFirstKeyBoardRow());
        keyboardRows.add(getSecondKeyBoardRow());
        keyboardRows.add(getThirdKeyBoardRow());

        return keyboardRows;
    }

    private static KeyboardRow getFirstKeyBoardRow() {
        KeyboardRow row = new KeyboardRow();
        row.add(TradersEnum.PRAPOR.getUserName());
        row.add(TradersEnum.THERAPIST.getUserName());
        row.add(TradersEnum.SKIER.getUserName());
        return row;
    }

    private static KeyboardRow getSecondKeyBoardRow() {
        KeyboardRow row = new KeyboardRow();
        row.add(TradersEnum.PEACEMAKER.getUserName());
        row.add(TradersEnum.RAGMAN.getUserName());
        return row;
    }

    private static KeyboardRow getThirdKeyBoardRow() {
        KeyboardRow row = new KeyboardRow();
        row.add(TradersEnum.MECHANIC.getUserName());
        row.add(TradersEnum.YAEGER.getUserName());
        row.add(TradersEnum.FENCE.getUserName());
        return row;
    }
}
