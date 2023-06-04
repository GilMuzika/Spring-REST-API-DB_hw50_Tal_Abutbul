package com.example.demo.repository;

import com.example.demo.model.Student;

import java.util.List;

public interface IStudentRepository {
    String createStudent(Student student);
    void updateStudent(Student student, Integer id);
    void deleteStudent(Integer id);
    List <Student> getAllStudents();
    Student getStudentById(Integer id);

    List<Student> getByExternalClass();
    List<Student> getByClassId(Integer class_id);
}
