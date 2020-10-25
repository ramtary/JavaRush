package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Charset win1251 = Charset.forName("Windows-1251");
        Charset utf8 = Charset.forName("UTF-8");

        FileInputStream fileInputStream = new FileInputStream(args[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);

        byte[] buffer = new byte[1000];
        while (fileInputStream.available() > 0) {
           fileInputStream.read(buffer);
           String s = new String(buffer, win1251);
           buffer = s.getBytes(utf8);
           fileOutputStream.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
