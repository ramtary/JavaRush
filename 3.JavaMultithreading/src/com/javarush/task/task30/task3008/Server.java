package com.javarush.task.task30.task3008;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();;
    
    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Сервер запущен...");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Что-то не так... сервер закрыт.");
        }
    }
    
    public static void sendBroadcastMessage(Message message) {
        try {
            for (Map.Entry<String, Connection> userConn: connectionMap.entrySet()) {
                userConn.getValue().send(message);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Что-то не так... сообщение не отправлено.");
        }
    }
    
    private static class Handler extends Thread {
        private Socket socket;
            
        public Handler(Socket socket) {
            this.socket = socket;
        }
        
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message userAnswer = connection.receive();
                
                if (userAnswer.getType() == MessageType.USER_NAME) {
                    if (!userAnswer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(userAnswer.getData())) {
                            connectionMap.put(userAnswer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return userAnswer.getData();
                        }
                    }
                }
            }
        }
        
        private void notifyUsers(Connection connection, String userName) throws IOException {
            try {
                for (Map.Entry<String, Connection> userConn: connectionMap.entrySet()) {
                    String name = userConn.getKey();
                    
                    Message command = new Message(MessageType.USER_ADDED, name);
                    if (!name.equals(userName)) {
                        connection.send(command);
                    }
                }
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Что-то не так...");
            }
        }
        
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message userMessage = connection.receive();
                
                if (userMessage.getType() == MessageType.TEXT) {
                    Message newMessage = new Message(MessageType.TEXT, userName + ": " + userMessage.getData());
                    sendBroadcastMessage(newMessage);
                } else {
                    ConsoleHelper.writeMessage("Амм... как бы, это не текст... что-то пошло не так");
                }
            }
        }
        
        public void run() {
            if (socket != null && socket.getRemoteSocketAddress() != null) {
                ConsoleHelper.writeMessage("Установлено соединение с сервером, по адресу:" + socket.getRemoteSocketAddress());
            }
            
            String userName = "";
            
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка на сервере!");
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("Соединение с сервером прервано.");
            }
        }
    }
}