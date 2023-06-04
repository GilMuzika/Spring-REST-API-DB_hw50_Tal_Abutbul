package com.example.demo.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ClassRoomMapper implements RowMapper<ClassRoom>{
    public ClassRoom mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new ClassRoom(
                rs.getInt("id"),
                rs.getInt("number_of_students"),
                rs.getFloat("class_avg"),
                TypeClass.valueOf(rs.getString("type_class"))
        );
    }
}
