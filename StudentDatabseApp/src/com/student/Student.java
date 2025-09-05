package com.student;

public class Student {

    private int id;
    private String name;
    private int age;
    private String course;
    private String email;

    public Student(int id, String name, int age, String course, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + age + " | " + course + " | " + email;
    }

    public static void main(String[] args) {
    }
}