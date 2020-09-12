package com.javarush.task.task02.task0210;

/* 
Необъективная реальность
*/
public class Solution {
    public static void main(String[] args) {
        Legend leg1 = new Legend();
        leg1.t_o_l = "Будешь плохо кодить, придет Java и съест твою память";
        System.out.println(leg1.t_o_l);
    }
    
    public static class Legend {
        public String t_o_l;
    }
}
