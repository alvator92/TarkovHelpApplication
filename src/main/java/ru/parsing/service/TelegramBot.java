package ru.parsing.service;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.parsing.common.StringConstant;
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
    @Autowired
    private ExecutionService executionService;
    @Autowired
    private FindQuestService findQuestService;
    @Autowired
    private CallBackResponseController callBackResponseController;

    private final BotConfiguration botConfiguration;

    static final String HELP_TEXT = "Этот бот выполнен для того, чтобы помочь тебе проходить квесты в игре EscapeFromTarkov\n\n" +
            "Вы можете ввести следующие команды из главного меню \n\n" +
            "Напишите /start , чтобы увидеть приветствие \n\n" +
            "Напишите /mydata , чтобы увидеть информацию о себе(нет) \n\n" +
            "Напишите /find , чтобы найти нужный квест введите(через пробел) \n\n" +
            "Напишите /quests , чтобы увидеть список торговцев и далее найти нужный квест \n\n" +
            "Напишите /help , чтобы снова увидеть это сообщение";

    /**
     * Инициализация главного меню
     */
    public TelegramBot(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Привет Бот!"));
        botCommandList.add(new BotCommand("/find", "Введите -> /find полное_имя_квеста"));
        botCommandList.add(new BotCommand("/quests", "Выберите искомый квест"));
        botCommandList.add(new BotCommand("/help", "Как пользоваться ботом"));
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
            getMessageTextFromUser(update);

        } else if (update.hasCallbackQuery()) {
            callBackResponseController.getCallbackDataFromUser(update);

        }
    }

    /**
     * Получение сообщения от пользователя
     */
    private void getMessageTextFromUser(Update update) {

        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        // отправка сообщений всем пользователям от админа
        if (messageText.contains("/send") && chatId == botConfiguration.getOwner()) {
            sendMessageToUsersFromOwner(messageText);
        } else if (messageText.contains("/find")) {
            var textToSend = messageText.substring(messageText.indexOf(" ")).trim();
            findQuestService.findQuestByName(chatId, textToSend);
        } else {
            switch (messageText) {
                case "/start" :
                    // проверка пользователя на наличие в БД и создание нового
                    registerUser(update.getMessage());
                    startCommandRecieved(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/help" :
                    executionService.prepareAndSendMessage(chatId, HELP_TEXT);
                    break;
                case "/quests" :
                    callBackResponseController.quests(chatId);
                    break;
                case "/image" :
                    String s = QuestImages.IMAGE_DESTINATION_FOLDER;
                    executionService.prepareAndSendMessage(chatId, s + "/1.jpg", "image" );
                    break;
                default:
                    executionService.prepareAndSendMessage(chatId, StringConstant.VALIDATION_ERROR_MESSAGE);

            }
        }
    }



    /**
     * Отправление рассылки всем пользователям от Владельца бота
     */
    private void sendMessageToUsersFromOwner(String messageText) {
        var textTosend = EmojiParser.parseToUnicode((messageText.substring(messageText.indexOf(" "))));
        var users = userRepository.findAll();
        for (User user : users) {
            executionService.prepareAndSendMessage(user.getChatId(), textTosend);
        }
    }

    /**
     * Проверка пользователя на наличие в БД. При отсутствии такового - создание нового пользователя.
     */
    private void registerUser(Message msg) {
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

    /**
     * Подготовка ответа при команде /start с использованием emoji
     */
    private void startCommandRecieved(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + ":blush:");
        executionService.prepareAndSendMessage(chatId, answer);
    }

}
