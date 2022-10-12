package com.manytomany.studentcourse.dto;

import com.manytomany.studentcourse.model.Course;
import com.manytomany.studentcourse.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String name;
    private Timestamp createdAt;
    private boolean isDeleted;
    private Set<Course> courses = new HashSet<>();

    public static Student dtoToMdo(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.name, studentDTO.createdAt, studentDTO.isDeleted, studentDTO.courses);
        return student;
    }

    public static StudentDTO mdoToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setCreatedAt(student.getCreatedAt());
        studentDTO.setDeleted(student.isDeleted());
        studentDTO.setCourses(student.getCourses());
        return studentDTO;
    }
}