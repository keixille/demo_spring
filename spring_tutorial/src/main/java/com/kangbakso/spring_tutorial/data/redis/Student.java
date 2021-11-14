package com.kangbakso.spring_tutorial.data.redis;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
public class Student implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;

    public Student(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    public String toString() {
        return "Student ID: " + this.id + "\n" +
                "Student Name: " + this.name + "\n" +
                "Student Gender: " + this.gender + "\n" +
                "Student Grade: " + this.grade;
    }
}
