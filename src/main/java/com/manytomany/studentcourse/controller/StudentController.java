package com.manytomany.studentcourse.controller;

import com.manytomany.studentcourse.dto.StudentDTO;
import com.manytomany.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/students")
    @ResponseBody
    public ResponseEntity<?> getAllStudents(@RequestBody StudentDTO studentDTO) {
        int id = studentService.addStudent(studentDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}")
    @ResponseBody
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
