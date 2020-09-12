package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        String sNum = Integer.toString(number);
        int res = 0;
        for (char chNum : sNum.toCharArray())
            res += Character.digit(chNum, 10);
        return res;
    }
}