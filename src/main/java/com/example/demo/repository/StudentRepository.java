package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository{
    protected static final String STUDENT_TABLE_NAME = "students";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String createStudent(Student student) {
        try {
            String query = String.format("INSERT INTO %s (first_name, last_name, avg_grade, gender, class_id) VALUES (?, ?, ?, ?, ?)", STUDENT_TABLE_NAME);
            jdbcTemplate.update(query, student.getFirst_name(), student.getLast_name(),
                    student.getAvg_grade(), student.getGender().name(),student.getClass_id());
            return null;
        }
        catch (Exception e) {
            return "{\"Error\": \"" + e.toString() + "\" }";
        }
    }

    @Override
    public void updateStudent(Student student, Integer id) {
        String query = String.format("UPDATE %s SET first_name=?, last_name=?, avg_grade=?, gender = ? WHERE id= ?", STUDENT_TABLE_NAME);
        jdbcTemplate.update(query, student.getFirst_name(), student.getLast_name(), student.getAvg_grade(),
                student.getGender().name(), id);
    }

    @Override
    public void deleteStudent(Integer id) {
        String query = String.format("DELETE FROM %s WHERE id= ?", STUDENT_TABLE_NAME);
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Student> getAllStudents() {
        String query = String.format("Select * from %s", STUDENT_TABLE_NAME);
        return jdbcTemplate.query(query, new StudentMapper());
    }

    @Override
    public Student getStudentById(Integer id) {
        String query = String.format("Select * from %s where id=?", STUDENT_TABLE_NAME);
        try
        {
            return jdbcTemplate.queryForObject(query, new StudentMapper(), id);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Student> getByExternalClass() {
        String query = String.format("SELECT *\n" +
                "FROM %s\n" +
                "WHERE class_id IN (\n" +
                "    SELECT id\n" +
                "    FROM %s\n" +
                "    WHERE type_class = 'EXTERNAL'\n" +
                ");",STUDENT_TABLE_NAME,ClassRoomRepository.CLASSROOM_TABLE_NAME);
        return  jdbcTemplate.query(query,new StudentMapper());
    }

    public List<Student> getByClassId(Integer class_id) {
        String query = String.format("Select * FROM %s WHERE class_id = ?",StudentRepository.STUDENT_TABLE_NAME);
        return jdbcTemplate.query(query,new StudentMapper(),class_id);
    }
}
