package com.example.demo.controller;

import com.example.demo.model.Gender;
import com.example.demo.model.RandomUserResponse;
import com.example.demo.model.Student;
import com.example.demo.service.ClassRoomService;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ClassRoomService classRoomService;

    @Autowired
    private UserServiceClient apiClient;

    @GetMapping
    public ResponseEntity get() {
        try {
            List<Student> result = studentService.getAllStudents();
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \" There are no students at all",
                    HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(value ="/{id}")
    public  ResponseEntity getById(@PathVariable Integer id){
        try {
            Student result = studentService.getStudentById(id);
            if (result != null) {
                return new ResponseEntity<Student>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \"not found student with Id " + id + "\" }",
                    HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/EXTERNAL")
    public ResponseEntity getByExternalClass(){
        try {
            List<Student> result = studentService.getByExternalClass();
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \" There are no students in external class",
                    HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody Student student) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(student);
        Student s = objectMapper.readValue(json, Student.class);
        String result = studentService.createStudent(student);
        if (result == null) {
            return new ResponseEntity<String>("{ \"result\": \"created\" }", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
    }


    @PutMapping(value ="/{id}")
    public ResponseEntity put(@RequestBody Student student, @PathVariable Integer id) {
        try {
            Student result = studentService.getStudentById(id);
            studentService.updateStudent(student, id);
            if (result != null) {
                return new ResponseEntity<Student>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"result\": \"created\" }", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try{
        Student result = studentService.getStudentById(id);
        if (result != null) {
            studentService.deleteStudent(id);
            return new ResponseEntity<Student>(result, HttpStatus.OK);
        }
        return new ResponseEntity<String>("{ \"Warning\": \"not found student with Id " + id + "\" }",
                HttpStatus.NOT_FOUND);
    }
        catch (Exception e) {
        return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity getRandomUser(@PathVariable Integer id){
        try {
        RandomUserResponse randomUserResponse = apiClient.getShow(id);
        Random rnd = new Random();
        List<Integer> temp = classRoomService.getAllClassRooms().stream().map(classRoom -> classRoom.getId()).toList();
        int min = temp.stream().min(Integer::compareTo).get();
        int max = temp.stream().max(Integer::compareTo).get();
        Student student = new Student(0,randomUserResponse.getFirst_name(),randomUserResponse.getLast_name(), rnd.nextFloat(60) + 40,Gender.valueOf(randomUserResponse.getGender().toUpperCase()),rnd.nextInt(max - min) + min);
        studentService.createStudent(student);
                return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/randomuser")
    public ResponseEntity getRandomUser(){
        try {
            Random rnd = new Random();
            RandomUserResponse randomUserResponse = apiClient.getShow(rnd.nextInt(1, 400));
            List<Integer> temp = classRoomService.getAllClassRooms().stream().map(classRoom -> classRoom.getId()).toList();
            int min = temp.stream().min(Integer::compareTo).get();
            int max = temp.stream().max(Integer::compareTo).get();
            Student student = new Student(0,randomUserResponse.getFirst_name(),randomUserResponse.getLast_name(), rnd.nextFloat(60) + 40,Gender.valueOf(randomUserResponse.getGender().toUpperCase()),rnd.nextInt(max - min) + min);
            studentService.createStudent(student);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
