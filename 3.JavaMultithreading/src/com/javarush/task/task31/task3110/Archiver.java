package com.javarush.task.task31.task3110;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Exception;
import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.Operation;
import com.javarush.task.task31.task3110.CommandExecutor;
import java.io.IOException;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;


public class Archiver {
    public static void main(String args[]) throws Exception {
        Operation currOper = null;
        
        do {
            try {
                currOper = askOperation();
                CommandExecutor.execute(currOper);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
                continue;
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
                continue;
            } 
        } while (currOper != Operation.EXIT);
         
    }
    
    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("%d - упаковать файлы в архив", Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("%d - упаковать файлы в архив", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("%d - упаковать файлы в архив", Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("%d - упаковать файлы в архив", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("%d - упаковать файлы в архив", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("%d - упаковать файлы в архив", Operation.EXIT.ordinal()));
        
        return Operation.values()[ConsoleHelper.readInt()];
    }
}