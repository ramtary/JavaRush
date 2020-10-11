package com.javarush.task.task30.task3008;

import java.io.*;


public class ConsoleHelper {
    private static BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
    
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    
    public static String readString() {
        String result;
        try {
            result = buffRead.readLine(); 
        } catch (IOException e) {
            writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз");
            return readString();
        }
        return result; 
    }
    
    public static int readInt() throws NumberFormatException, IOException {
        int result;
        try {
            result = Integer.parseInt(readString()); 
        } catch (NumberFormatException e) {
            writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз");
            return readInt();
        }
        return result;
    }
}