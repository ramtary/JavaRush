package com.javarush.task.task30.task3010;

import java.util.regex.Pattern;
import java.math.BigInteger;
import java.math.BigDecimal;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            for (int i = 2; i < 37; i++) {
                if (isNum(args[0], i)) {
                    System.out.println(i);
                    break;
                } else 
                    if (i == 36) System.out.println("incorrect");
            }
        } catch (Exception e) {
            
        }
    }
    
    public static boolean isNum(String num, int radix) {
        boolean result = false;
        try {
            new BigDecimal(new BigInteger(num, radix));
            result = true;
        } catch (Exception e) {
            
        }
        return result;
    }
}