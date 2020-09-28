package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class University {
    private List<Student> students;
    private String name;
    private int age;
    
    public University(String name, int age) {
        this.name = name;
        this.age = age;
        this.students = new ArrayList<>();
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getAge() {
        return this.age;
    }

    
    public List<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(List<Student> st) {
        this.students = st;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student st: students) {
            if (st.getAverageGrade() == averageGrade)
                return st;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxAverageGrade = students.get(0).getAverageGrade();
        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() > maxAverageGrade)
                maxAverageGrade = students.get(i).getAverageGrade();
        }
        return getStudentWithAverageGrade(maxAverageGrade);
    }

    public Student getStudentWithMinAverageGrade() {
        double minAverageGrade = students.get(0).getAverageGrade();
        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() < minAverageGrade)
                minAverageGrade = students.get(i).getAverageGrade();
        }
        return getStudentWithAverageGrade(minAverageGrade);
    }
    
    public void expel(Student student) {
        this.students.remove(student);
    }
}