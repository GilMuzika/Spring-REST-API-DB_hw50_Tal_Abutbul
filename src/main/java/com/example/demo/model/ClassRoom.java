package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

public class ClassRoom {
    @Getter @Setter
    protected Integer id;
    @Getter @Setter
    protected Integer number_of_students;
    @Getter @Setter
    protected Float class_avg;
    @Getter @Setter
    protected TypeClass type_class;

    public ClassRoom(){}

    public ClassRoom(Integer id, Integer number_of_students, Float class_avg, TypeClass type_class) {
        this.id = id;
        this.number_of_students = number_of_students;
        this.class_avg = class_avg;
        this.type_class = type_class;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", number_of_students=" + number_of_students +
                ", class_avg=" + class_avg +
                ", externalClass=" + type_class.name() +
                '}';
    }
}
