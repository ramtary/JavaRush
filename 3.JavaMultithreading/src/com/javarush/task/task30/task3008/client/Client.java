package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;
import com.javarush.task.task30.task3008.ConsoleHelper;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread {
    protected Connection connection;
    private volatile boolean clientConnected = false;
    
    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера:");
        
        return ConsoleHelper.readString();
    }
    
    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        try {
            return ConsoleHelper.readInt();
        } catch (IOException e) {
            return 0;
        }
    }
    
    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        
        return ConsoleHelper.readString();
    }
    
    protected boolean shouldSendTextFromConsole() {
        return true;
    }
    
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }
    
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("что-то пошло не так...");
            clientConnected = false;
        }
    }
    
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        
        try {
            synchronized (this) {
              wait();  
            };
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Что-то пошло не так...");
            System.exit(1);
        }
        
        if  (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");  
            while (clientConnected) {
                String userMess = ConsoleHelper.readString();
                if (userMess.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    if (shouldSendTextFromConsole()) {
                        sendTextMessage(userMess);
                    }
                }
            }
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента."); 
        }
        
    }
    
    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }
        
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник " + userName + " присоединился к чату.");
        }
        
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник " + userName + " покинул чат.");
        }
        
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }
        
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message newMessage = null;
            while (!clientConnected) {
                try {
                    newMessage = connection.receive();
                } catch (Exception e) {
                    ConsoleHelper.writeMessage("Что-то пошло не так...");
                }
                if (newMessage.getType() == MessageType.NAME_REQUEST) {
                    String userName = getUserName(); 
                    Message newUser = new Message(MessageType.USER_NAME, userName);
                    connection.send(newUser);
                } else {
                    if (newMessage.getType() == MessageType.NAME_ACCEPTED) {
                        notifyConnectionStatusChanged(true);
                    } else {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }
        
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            Message newMessage = null;
            while (true) {
                try {
                    newMessage = connection.receive();
                } catch (Exception e) {
                    ConsoleHelper.writeMessage("Что-то пошло не так...");
                }
                
                if (newMessage.getType() == MessageType.TEXT) 
                    processIncomingMessage(newMessage.getData());
                else
                    if (newMessage.getType() == MessageType.USER_ADDED)
                        informAboutAddingNewUser(newMessage.getData());
                    else
                        if (newMessage.getType() == MessageType.USER_REMOVED)
                            informAboutDeletingNewUser(newMessage.getData());
                        else
                            throw new IOException("Unexpected MessageType");
                        
            }
        }
        
        public void run() {
            try {
                String serverAddress = getServerAddress();
                int serverPort = getServerPort();
            
                Socket socket = new Socket(serverAddress, serverPort);
                Client.this.connection = new Connection(socket);
            
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
            
        }
        
    }
    
    
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}