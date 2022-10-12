package com.manytomany.studentcourse.controller;

import com.manytomany.studentcourse.dto.CourseDTO;
import com.manytomany.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @PostMapping("/courses")
    @ResponseBody
    public ResponseEntity<?> getAllCourses(@RequestBody CourseDTO courseDTO) {
        int id = courseService.addCourse(courseDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    @ResponseBody
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(courseService.updateCourse(id, courseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }
}

