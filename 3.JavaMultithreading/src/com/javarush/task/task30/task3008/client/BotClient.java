package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BotClient extends Client {
    
    @Override 
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }
    
    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }
    
    @Override
    protected String getUserName() {
        String userName = "date_bot_"+(int) (Math.random()*100);
        return userName;
    }
    
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
        
        @Override
        protected void processIncomingMessage(String message) {
            if (message != null) {
                ConsoleHelper.writeMessage(message);
                if (message.contains(": ")) {
                    String senderName = message.split(":")[0];
                    String senderMess = message.split(":")[1].trim();
                
                    SimpleDateFormat sDf = null;
                    switch (senderMess) {
                        case "дата":
                            sDf = new SimpleDateFormat("d.MM.YYYY");
                            break;
                        case "день":
                            sDf = new SimpleDateFormat("d");
                            break;
                        case "месяц":
                            sDf = new SimpleDateFormat("MMMM");
                            break;
                        case "год":
                            sDf = new SimpleDateFormat("YYYY");
                            break;
                        case "время":
                            sDf = new SimpleDateFormat("H:mm:ss");
                            break;
                        case "час":
                            sDf = new SimpleDateFormat("H");
                            break;
                        case "минуты":
                            sDf = new SimpleDateFormat("m");
                            break;
                        case "секунды":
                            sDf = new SimpleDateFormat("s");
                            break;
                    }
                    if (sDf != null)
                        sendTextMessage(String.format("Информация для %s: %s" , senderName, sDf.format(Calendar.getInstance().getTime())));
                }
                
            }
        }
    }    
    
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}