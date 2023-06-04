package com.example.demo.repository;

import com.example.demo.model.ClassRoom;
import com.example.demo.model.Student;

import java.util.List;

public interface IClassRoomRepository {
    String createClassRoom(ClassRoom classRoom);
    void updateClassRoom(ClassRoom classRoom, Integer id);
    void deleteClassRoom(Integer id);
    void calcAvgAndStudentCount(ClassRoom classRoom,Integer id);
    List<ClassRoom> getAllClassRooms();
    ClassRoom getClassRoomById(Integer id);
    List<Student>getByExternalClass();

}
