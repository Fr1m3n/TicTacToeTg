import Conditions.Condition;
import Conditions.TestCondition1;
import Entities.UserEntity;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Logger;


public class Bot extends TelegramLongPollingBot {

    private HashMap<String, Condition> conditionMap;

    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        sendMsg(chatId.toString(),
                "Hoooy");
        UserEntity user = new UserEntity(chatId);
        conditionMap.get(user.getCondition()).f(update);
    }

    /**
     * Функция для инициализации хеш таблицы состояний
     */
    public void initConditions(){
        conditionMap.put("start", new TestCondition1());
    }

    /**
     * Функция отправки сообщения
     * @param chatId - id получателя
     * @param message - текст сообщения
     */
    public void sendMsg(String chatId,
                        String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            Message msg =  execute(sendMessage);
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(msg.getChatId().toString());
            deleteMessage.setMessageId(msg.getMessageId());
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }


    public String getBotUsername() {
        return "TicTacToeBot";
    }

    public String getBotToken() {
        return "919157632:AAF8_YxFcYqKTaUkGxbUA6FGYV5RTdMYONM";
    }
}
