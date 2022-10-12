package com.manytomany.studentcourse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@SQLDelete(sql = "UPDATE student SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
    @JsonProperty("createdAt")
    @Column(name = "created_at")
    private Timestamp createdAt;
    @JsonProperty("isDeleted")
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "aws_s3.STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "COURSE_ID") })
    private Set<Course> courses;

    public Student(String name, Timestamp createdAt, boolean isDeleted, Set<Course> courses) {
        this.name = name;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.courses = courses;
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.createdAt, this.isDeleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Student))
            return false;
        Student student = (Student) o;
        return Objects.equals(this.id, student.id)
                && Objects.equals(this.name, student.name)
                && Objects.equals(this.createdAt, student.createdAt)
                && Objects.equals(this.isDeleted, student.isDeleted);
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + this.id + '\'' + ", name='" + this.name
                + '\'' + ", createdAt='" + this.createdAt + '\'' + ", isDeleted='" + this.isDeleted
                + '\'' + ", students='" + this.courses + '\'' + '}';
    }
}
