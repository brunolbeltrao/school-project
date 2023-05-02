package br.com.school.repository;

import br.com.school.hexagono.domain.Course;
import br.com.school.hexagono.domain.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class StudentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="STUDENT_COURSE",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    private Set<CourseEntity> courses = new HashSet<>();

    public StudentEntity() {
    }

    public StudentEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudentEntity(Long id, String name,Set<CourseEntity> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }
}
