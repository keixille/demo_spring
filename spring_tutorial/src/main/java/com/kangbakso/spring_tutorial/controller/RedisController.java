package com.kangbakso.spring_tutorial.controller;

import com.kangbakso.spring_tutorial.entity.redis.Student;
import com.kangbakso.spring_tutorial.repository.redis.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/redis/create")
    public String createStudent() {
        Student student = new Student("Eng2015001", "John Doe", Student.Gender.MALE, 1);
        studentRepository.save(student);
        return "Created";
    }

    @GetMapping("/redis/get")
    public String getStudent() {
        Student retrievedStudent = studentRepository.findById("Eng2015001").get();
        return retrievedStudent.toString();
    }
}
