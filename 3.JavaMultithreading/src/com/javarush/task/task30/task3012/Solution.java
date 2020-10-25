package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        int[] nums = {1, 3, 9, 27, 81, 243, 729, 2187};
        
        String s = "";
        while (number > 0) {
            if (number % 3 == 0) {
                s = s + "0";
                number /= 3;
            } else if (number % 3 == 1) {
                s = s + "+";
                number /= 3;
            } else if (number % 3 == 2) {
                s = s + "-";
                number /= 3;
                number++;
            }
        }
        
        int summ = 0;
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                str += " + " + nums[i];
                summ += nums[i];
            } else if (s.charAt(i) == '-') {
                str += " - " + nums[i];
                summ -= nums[i];
            }
        }
        System.out.println(summ + " = " + str);
    }
}