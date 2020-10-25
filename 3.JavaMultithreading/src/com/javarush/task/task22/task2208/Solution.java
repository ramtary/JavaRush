package com.javarush.task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (param.getValue() != null) {
                if (sb.length() > 0)
                    sb.append(" and " + param.getKey() + " = '" + param.getValue() + "'");
                else
                    sb.append(param.getKey() + " = '" + param.getValue() + "'");
            }
        }


        return sb.toString();
    }
}
