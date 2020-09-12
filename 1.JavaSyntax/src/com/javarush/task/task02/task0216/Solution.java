package com.javarush.task.task02.task0216;

/* 
Минимум трёх чисел
*/
public class Solution {
    public static int min(int a, int b, int c) {
        int r1;
        if (a < b)
            r1 = a;
        else
            r1 = b;
        if (r1 < c)
            return r1;
        else
            return c;
    }

    public static void main(String[] args) {
        System.out.println(min(1, 2, 3));
        System.out.println(min(-1, -2, -3));
        System.out.println(min(3, 5, 3));
        System.out.println(min(5, 5, 10));
    }
}