package com.example.demo.model;
import java.util.List;
public class MyDTOResponse   {
protected ClassRoom classRoom;
protected List<Student> studentList;
public MyDTOResponse(){}
    public MyDTOResponse(ClassRoom classRoom, List<Student> studentList) {
        this.classRoom = classRoom;
        this.studentList = studentList;
    }

    public MyDTOResponse getMyDTO(ClassRoom classRoom,List<Student> studentList){
    return new MyDTOResponse(classRoom,studentList);
    }

    @Override
    public String toString() {
        return "MyDTOResponse{" +
                "classRoom=" + classRoom +
                ", studentList=" + studentList +
                '}';
    }
}
