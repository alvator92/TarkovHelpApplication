package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.parsing.common.StringConstant;
import ru.parsing.emun.TradersEnum;

import java.util.List;

@Component
public class CallBackResponseController {

    @Autowired
    private ExecutionService executionService;
    @Autowired
    private QuestController questController;
    @Autowired
    private FindQuestService findQuestService;

    /**
     * Получение ответа после того как пользователь нажимает кнопку ДА/НЕТ
     */
    public void getCallbackDataFromUser(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        if (callbackData.equals(StringConstant.YES_BUTTON)) {
            String text = "Окей, какой торговец тебя интересует?";
            // Обработка сообщещний по кнопке
            executionService.executionEditMessage(TradersService.setCallbackMessage(messageId, chatId, text));
        } else if (callbackData.equals(StringConstant.NO_BUTTON)) {
            String text = "Надумаешь, приходи!";
            // Обработка сообщещний по кнопке
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, text));
        } else if (isTrader(callbackData)) {
            String text = "Выбирай >>>>>>>";
            List<String> listOfQuests = questController.findQuestsNameByTrader(callbackData);
            executionService.executionEditMessage(ListOfQuestService.setCallbackMessage(messageId, chatId, text, listOfQuests));

        } else {
            findQuestService.findQuestByName(chatId, callbackData);
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, "Бот в помощь!"));
        }
    }

    // Проверка на имя торговца
    private boolean isTrader(String callbackData) {
        for (TradersEnum value : TradersEnum.values()) {
            if (callbackData.equals(value.getUserName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Обработка сообщещний после нажатия кнопки YES/NO
     */
    private EditMessageText setCallbackMessage(long messageId, long chatId, String text) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(text);
        messageText.setMessageId((int)messageId);
        return messageText;
    }

    public void quests(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Вы хотите увидеть какие квесты есть у тороговцев?");
        message.setReplyMarkup(InlineKeeBoardService.getInlineKeeBoard());
        executionService.executeMessage(message);
    }
}
