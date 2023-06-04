package com.example.demo.service;

import com.example.demo.model.Student;


import java.util.List;


public interface IStudentService {
    String createStudent(Student student);
    void updateStudent(Student student, Integer id);
    void deleteStudent(Integer id);
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    List<Student>getByExternalClass();
}
