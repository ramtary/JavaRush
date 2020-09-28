package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Stack<Throwable> exceptions = new Stack<>();
        exceptions.push(e);
        Throwable nextExcep = e.getCause();
        while (nextExcep != null) {
            exceptions.push(nextExcep);
            nextExcep = nextExcep.getCause();
        }
        
        while (!exceptions.empty()) {
            Throwable currExcep = exceptions.pop();
            System.out.println(currExcep.getClass().getName() + ": " + currExcep.getMessage());
        }
    }

    public static void main(String[] args) {
    }
}
