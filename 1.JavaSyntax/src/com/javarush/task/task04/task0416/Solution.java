package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double time = Double.parseDouble(reader.readLine());
        double wc = Math.abs(time % 5);
        if (wc >= 0 & wc < 3)
            System.out.println("зелёный");
        else
            if (wc >= 3 & wc < 4)
                System.out.println("жёлтый");
            else
                System.out.println("красный");

    }
}