package com.example.demo.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentMapper implements RowMapper<Student> {
    public Student mapRow(ResultSet rs,int rowNum) throws SQLException{
        return new Student(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getFloat("avg_grade"),
                Gender.valueOf(rs.getString("gender")),
                rs.getInt("class_id")
        );

    }
}
