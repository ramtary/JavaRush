package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) {
        if (string == null || string.isEmpty())
            throw new TooShortStringException();

        String[] parts = string.split(" ");
        if (parts.length<5)
            throw new TooShortStringException();

        return parts[1] + " " + parts[2] + " " + parts[3] + " " + parts[4];
    }

    public static class TooShortStringException extends RuntimeException {

    }
}
