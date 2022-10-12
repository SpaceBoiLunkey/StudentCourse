package com.manytomany.studentcourse.service;

import com.manytomany.studentcourse.dto.StudentDTO;
import com.manytomany.studentcourse.exception.StudentNotFoundException;
import com.manytomany.studentcourse.model.Student;
import com.manytomany.studentcourse.repository.CourseRepository;
import com.manytomany.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Transactional
    public int addStudent(StudentDTO studentDTO) {
        Student student = StudentDTO.dtoToMdo(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return savedStudent.getId();
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(student -> {
            StudentDTO dto = StudentDTO.mdoToDto(student);
            studentDTOS.add(dto);
        });
        return studentDTOS;
    }

    public StudentDTO getStudentById(int id) {
        Optional<Student> result = studentRepository.findById(id);
        if (result.isPresent()) {
            Student student = result.get();
            StudentDTO dto = StudentDTO.mdoToDto(student);
            return dto;
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
        StudentDTO studentDTONew = null;
        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            student.setName(studentDTO.getName());
            studentDTONew = StudentDTO.mdoToDto(student);
            return studentDTONew;
        }
        return studentDTONew;
    }
}
