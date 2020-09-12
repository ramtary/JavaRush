package com.javarush.task.task03.task0307;

/* 
Привет StarCraft!
*/

public class Solution {
    public static void main(String[] args) {
        Zerg z1 = new Zerg();
        z1.name = "zy";
        Zerg z2 = new Zerg();
        z2.name = "zu";
        Zerg z3 = new Zerg();
        z3.name = "zi";
        Zerg z4 = new Zerg();
        z4.name = "zo";
        Zerg z5 = new Zerg();
        z5.name = "zp";

        Protoss p1 = new Protoss();
        p1.name = "py";
        Protoss p2 = new Protoss();
        p2.name = "pu";
        Protoss p3 = new Protoss();
        p3.name = "pi";

        Terran t1 = new Terran();
        t1.name = "ty";
        Terran t2 = new Terran();
        t2.name = "tu";
        Terran t3 = new Terran();
        t3.name = "ti";
        Terran t4 = new Terran();
        t4.name = "to";
    }

    public static class Zerg {
        public String name;
    }

    public static class Protoss {
        public String name;
    }

    public static class Terran {
        public String name;
    }
}
