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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.parsing.configuration.BotConfiguration;
import ru.parsing.configuration.JpaConfig;
import ru.parsing.dto.User;
import ru.parsing.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
            "Напишите /mydata , чтобы увидеть информацию о себе \n\n" +
            "Напишите /help , чтобы снова увидеть это сообщение";

    public TelegramBot(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Get a welcome message"));
        botCommandList.add(new BotCommand("/mydata", "Get you data stored"));
        botCommandList.add(new BotCommand("/deletedata", "Delete my data"));
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
                default:
                    sendMessage(chatId, "Sorry, command was not found");

            }
        }

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

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getKeeBoard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Прапор");
        row.add("Терапевт");
        row.add("Лыжник");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Миротворец");
        row.add("Барахольщик");
        keyboardRows.add(row);


        row = new KeyboardRow();
        row.add("Механик");
        row.add("Егерь");
        row.add("Скупщик");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;

    }
}
