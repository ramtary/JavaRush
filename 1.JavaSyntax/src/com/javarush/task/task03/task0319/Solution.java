package com.javarush.task.task03.task0319;

/* 
Предсказание на будущее
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String name = reader.readLine();
        String sNum = reader.readLine();
        String sNum2 = reader.readLine();

        System.out.println(name + " получает " + sNum + " через " + sNum2 + " лет.");

    }
}
