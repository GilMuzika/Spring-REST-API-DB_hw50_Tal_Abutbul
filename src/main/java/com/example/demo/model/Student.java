package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

public class Student {
    @Getter@Setter
    protected Integer id;
    @Getter@Setter
    protected String first_name;
    @Getter@Setter
    protected String last_name;
    @Getter@Setter
    protected Float avg_grade;
    @Getter@Setter
    protected Gender gender;
    @Getter@Setter
    protected Integer class_id;

    public Student(){}

    public Student(Integer id, String first_name, String last_name, Float avg_grade, Gender gender, Integer class_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avg_grade = avg_grade;
        this.gender = gender;
        this.class_id = class_id;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", avgGrade=" + avg_grade +
                ", gender=" + gender.name() +
                ", class_id=" + class_id +
                '}';
    }
}
