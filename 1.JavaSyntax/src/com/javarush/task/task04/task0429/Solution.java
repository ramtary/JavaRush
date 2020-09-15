package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        int p = 0;
        int m = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[3];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        for(int value : arr)
            if (value != 0)
                if (value > 0)
                    p++;
                else
                    m++;

        System.out.println("количество отрицательных чисел: "+ m);
        System.out.println("количество положительных чисел: "+ p);

    }
}
