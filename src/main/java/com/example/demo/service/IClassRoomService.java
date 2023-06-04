package com.example.demo.service;

import com.example.demo.model.ClassRoom;
import com.example.demo.model.MyDTOResponse;
import com.example.demo.model.Student;

import java.util.List;

public interface IClassRoomService {
    String createClassRoom(ClassRoom classRoom);
    void updateClassRoom(ClassRoom classRoom, Integer id);
    void deleteClassRoom(Integer id) throws Exception;
    List<ClassRoom> getAllClassRooms();
    ClassRoom getClassRoomById(Integer id);
    List<Student>getByExternalClass();
    MyDTOResponse getMyDTO(Integer id);
}
