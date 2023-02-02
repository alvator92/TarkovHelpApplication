package ru.parsing.service;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.parsing.common.StringConstant;
import ru.parsing.configuration.BotConfiguration;
import ru.parsing.configuration.JpaConfig;
import ru.parsing.dto.User;
import ru.parsing.emun.TradersEnum;
import ru.parsing.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private JpaConfig jpaConfig;
    @Autowired
    private UserRepository userRepository;

    private final BotConfiguration botConfiguration;

    static final String HELP_TEXT = "Этот бот выполнен для того, чтобы помочь тебе проходить квесты в игре EscapeFromTarkov\n\n" +
            "Вы можете ввести следующие команды из главного меню \n\n" +
            "Напишите /start , чтобы увидеть приветствие \n\n" +
            "Напишите /mydata , чтобы увидеть информацию о себе \n\n" +
            "Напишите /register , чтобы  \n\n" +
            "Напишите /help , чтобы снова увидеть это сообщение";

    public TelegramBot(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Get a welcome message"));
        botCommandList.add(new BotCommand("/mydata", "Get you data stored"));
        botCommandList.add(new BotCommand("/deletedata", "Delete my data"));
        botCommandList.add(new BotCommand("/register", "Registration"));
        botCommandList.add(new BotCommand("/help", "Info how to use Bot"));
        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e ) {
            log.error("Error setting bot`s command list : " + e.getMessage());
        }

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
                    registerUser(update.getMessage());
                    startCommandRecieved(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/help" :
                    sendMessage(chatId, HELP_TEXT);
                    break;
                case "/register" :
                    register(chatId);
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not found");

            }
        }

    }

    private void register(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Do you real want to register?");
        message.setReplyMarkup(getInlineKeeBoard());
        executionMessage(message);
    }



    private void registerUser(Message msg) {

//        if (userRepository.findById(msg.getChatId()).isEmpty()) {
        if (jpaConfig.userService().findById(msg.getChatId()).isEmpty()) {

            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User.Builder()
                    .withChatId(chatId)
                    .withFirstName(chat.getFirstName())
                    .withLastName(chat.getLastName())
                    .withUserName(chat.getUserName())
                    .withRegisterAt(new Timestamp(System.currentTimeMillis()))
                    .build();

            jpaConfig.userService().save(user);
            log.info("User saved :" + user);
        }
    }

    private void startCommandRecieved(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + ":blush:");
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        message.setReplyMarkup(getKeeBoard());
        executionMessage(message);

    }

    private void executionMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getKeeBoard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(getKeyBoardRows());
        return keyboardMarkup;

    }

    private List<KeyboardRow> getKeyBoardRows() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        keyboardRows.add(getFirstKeyBoardRow());
        keyboardRows.add(getSecondKeyBoardRow());
        keyboardRows.add(getThirdKeyBoardRow());

        return keyboardRows;
    }

    private KeyboardRow getFirstKeyBoardRow() {
        KeyboardRow row = new KeyboardRow();
        row.add(TradersEnum.PRAPOR.getUserName());
        row.add(TradersEnum.THERAPIST.getUserName());
        row.add(TradersEnum.SKIER.getUserName());
        return row;
    }

    private KeyboardRow getSecondKeyBoardRow() {
        KeyboardRow row = new KeyboardRow();
        row.add(TradersEnum.PEACEMAKER.getUserName());
        row.add(TradersEnum.RAGMAN.getUserName());
        return row;
    }

    private KeyboardRow getThirdKeyBoardRow() {
        KeyboardRow row = new KeyboardRow();
        row.add(TradersEnum.MECHANIC.getUserName());
        row.add(TradersEnum.YAEGER.getUserName());
        row.add(TradersEnum.FENCE.getUserName());
        return row;
    }

    private InlineKeyboardMarkup getInlineKeeBoard() {

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        rowInline.add(getYesButton());
        rowInline.add(getNoButton());

        rowsInLine.add(rowInline);

        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }

    private InlineKeyboardButton getYesButton() {
        var yesButton = new InlineKeyboardButton();
        yesButton.setText(StringConstant.YES);
        yesButton.setCallbackData(StringConstant.YES_BUTTON);
        return yesButton;
    }

    private InlineKeyboardButton getNoButton() {
        var noButton = new InlineKeyboardButton();
        noButton.setText(StringConstant.NO);
        noButton.setCallbackData(StringConstant.NO_BUTTON);
        return noButton;
    }
}
