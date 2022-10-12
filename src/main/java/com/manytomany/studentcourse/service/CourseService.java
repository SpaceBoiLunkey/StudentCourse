package com.manytomany.studentcourse.service;

import com.manytomany.studentcourse.dto.CourseDTO;
import com.manytomany.studentcourse.exception.CourseNotFoundException;
import com.manytomany.studentcourse.model.Course;
import com.manytomany.studentcourse.repository.CourseRepository;
import com.manytomany.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.manytomany.studentcourse.dto.CourseDTO.mdoToDto;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public int addCourse(CourseDTO courseDTO) {
        Course course = CourseDTO.dtoToMdo(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return savedCourse.getId();
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        courses.forEach(course -> {
            CourseDTO dto = mdoToDto(course);
            courseDTOS.add(dto);
        });
        return courseDTOS;
    }

    public CourseDTO getCourseById(int id) {
        Optional<Course> result = courseRepository.findById(id);
        if (result.isPresent()) {
            Course course = result.get();
            CourseDTO dto = mdoToDto(course);
            return dto;
        } else {
            throw new CourseNotFoundException(id);
        }
    }

    public List<CourseDTO> getCoursesByStudentId(String name) {
        List<Course> courses = courseRepository.findByName(name);
        return courses.stream().map(course -> mdoToDto(course)).collect(Collectors.toList());
    }

    public void deleteCourseById(int id) {
        courseRepository.deleteById(id);
    }

    public CourseDTO updateCourse(int id, CourseDTO courseDTO) {
        CourseDTO courseDTONew = null;
        if (courseRepository.findById(id).isPresent()) {
            Course course = courseRepository.findById(id).get();
            course.setName(courseDTO.getName());
            courseDTONew = mdoToDto(course);
            return courseDTONew;
        }
        return courseDTONew;
    }
}
