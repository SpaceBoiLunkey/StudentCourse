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
public class CourseDTO {
    private Integer id;
    private String name;
    private Timestamp createdAt;
    private boolean isDeleted;
    private Set<Student> students = new HashSet<>();

    public static Course dtoToMdo(CourseDTO courseDTO) {
        Course course = new Course(courseDTO.name, courseDTO.createdAt, courseDTO.isDeleted, courseDTO.students);
        return course;
    }

    public static CourseDTO mdoToDto(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setCreatedAt(course.getCreatedAt());
        courseDTO.setDeleted(course.isDeleted());
        courseDTO.setStudents(course.getStudents());
        return courseDTO;
    }


}
