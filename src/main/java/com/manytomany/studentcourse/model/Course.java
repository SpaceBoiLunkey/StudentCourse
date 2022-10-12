package com.manytomany.studentcourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE student SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Course {
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
    @ManyToMany(mappedBy = "courses")
    @Column(name = "students")
    @JsonIgnore
    private Set<Student> students;

    public Course(String name, Timestamp createdAt, boolean isDeleted, Set<Student> students) {
        this.name = name;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.students = students;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.createdAt, this.isDeleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Course))
            return false;
        Course course = (Course) o;
        return Objects.equals(this.id, course.id)
                && Objects.equals(this.name, course.name)
                && Objects.equals(this.createdAt, course.createdAt)
                && Objects.equals(this.isDeleted, course.isDeleted);
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + this.id + '\'' + ", name='" + this.name
                + '\'' + ", createdAt='" + this.createdAt + '\'' + ", isDeleted='" + this.isDeleted
                + '\'' + ", students='" + this.students + '\'' + '}';
    }
}
