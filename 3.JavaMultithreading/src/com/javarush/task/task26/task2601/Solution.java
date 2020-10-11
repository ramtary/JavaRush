package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;


/* 
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        int med, len = array.length;
        med = len % 2 != 0 ? array[len / 2] : (int) ((array[len / 2] + array[len / 2 - 1]) / 2);
        
        Comparator<Integer> copmarator = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                double result = Math.abs(o1 - med) - Math.abs(o2 - med);    
                return result == 0 ? o1 - o2 : (int) Math.round(result);
            } 
        };
        
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        Collections.sort(list, copmarator);
        
        Integer[] result = list.toArray(new Integer[list.size()]);
        
        return result;
    }
}
