package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder result = new StringBuilder();
        result.append(a);
        
        int current = a;
        
        if (a == b) {
            return Integer.toString(a);
        } else {
            if (a > b) {
                while (current > b) {
                    result.append(" ").append(--current);
                }
                
            } else {
                while (current < b) {
                    result.append(" ").append(++current);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}