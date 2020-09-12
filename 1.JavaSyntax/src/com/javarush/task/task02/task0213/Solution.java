package com.javarush.task.task02.task0213;

/* 
Питомцам нужны люди
*/
public class Solution {
    public static void main(String[] args) {
        Cat vaska = new Cat();
        Dog sharik = new Dog();
        Fish som = new Fish();
        Woman julia = new Woman();
        
        vaska.owner = julia;
        sharik.owner = julia;
        som.owner = julia;
    }

    public static class Cat {
        public Woman owner;
    }

    public static class Dog {
        public Woman owner;
    }

    public static class Fish {
        public Woman owner;
    }

    public static class Woman {
    }
}
