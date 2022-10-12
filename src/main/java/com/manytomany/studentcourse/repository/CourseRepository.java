package com.manytomany.studentcourse.repository;

import com.manytomany.studentcourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    public List<Course> findByName(String name);
}
