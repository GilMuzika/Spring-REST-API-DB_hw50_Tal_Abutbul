package com.example.demo.service;

import com.example.demo.model.ClassRoom;
import com.example.demo.model.MyDTOResponse;
import com.example.demo.model.Student;
import com.example.demo.repository.ClassRoomRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomService implements IClassRoomService{

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Value("${min_of_student_class}")
    private Integer min_of_student_class;


    @Override
    public String createClassRoom(ClassRoom classRoom) {
       return classRoomRepository.createClassRoom(classRoom);
    }

    @Override
    public void updateClassRoom(ClassRoom classRoom, Integer id) {
        classRoomRepository.updateClassRoom(classRoom,id);
    }

    @Override
    public void deleteClassRoom(Integer id) throws Exception {
        if (classRoomRepository.getClassRoomById(id).getNumber_of_students() >= min_of_student_class){
            throw new Exception("You cannot delete a class that has students");
        }
        classRoomRepository.deleteClassRoom(id);
    }

    @Override
    public List<ClassRoom> getAllClassRooms() {
        List <Integer> result = studentRepository.getAllStudents().stream().map(student -> student.getClass_id()).distinct().toList();
        for (Integer id : result){
            classRoomRepository.calcAvgAndStudentCount(classRoomRepository.getClassRoomById(id),id);
        }
        return classRoomRepository.getAllClassRooms();
    }

    @Override
    public ClassRoom getClassRoomById(Integer id) {
        return classRoomRepository.getClassRoomById(id);
    }

    @Override
    public List<Student> getByExternalClass() {
        return classRoomRepository.getByExternalClass();
    }

    @Override
    public MyDTOResponse getMyDTO(Integer id) {
        classRoomRepository.calcAvgAndStudentCount(classRoomRepository.getClassRoomById(id),id);
       return new MyDTOResponse(classRoomRepository.getClassRoomById(id),studentRepository.getByClassId(id));
    }
}
