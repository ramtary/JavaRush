package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String sA = reader.readLine();
        int a = Integer.parseInt(sA);
        reader.close();

        switch (sA.length()) {
            case (1):
                if (a >= 1 && a <= 999)
                    if (a % 2 == 0)
                        System.out.println("четное однозначное число");
                    else
                        System.out.println("нечетное однозначное число");
                break;
            case (2):
                if (a >= 1 && a <= 999)
                    if (a % 2 == 0)
                        System.out.println("четное двузначное число");
                    else
                        System.out.println("нечетное двузначное число");
                break;
            case (3):
                if (a >= 1 && a <= 999)
                    if (a % 2 == 0)
                        System.out.println("четное трехзначное число");
                    else
                        System.out.println("нечетное трехзначное число");
                break;

        }



    }
}
