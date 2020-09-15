package com.javarush.task.task04.task0441;

/* 
Как-то средненько
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));

        Integer[] arr = new Integer[3];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(arr);

        if (arr[0] == arr[1] & arr[1] == arr[2])
            System.out.println(arr[0]);
        else
            System.out.println(arr[1]);

        reader.close();





    }
}
