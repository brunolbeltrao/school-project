package br.com.school.repository;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="course")
public class CourseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<StudentEntity> studentList = new HashSet<>();

    public CourseEntity() {
    }

    public CourseEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseEntity(Long id, String name,Set<StudentEntity> studentList) {
        this.id = id;
        this.name = name;
        this.studentList = studentList;
    }

}
