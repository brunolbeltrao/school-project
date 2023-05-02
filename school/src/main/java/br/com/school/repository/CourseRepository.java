package br.com.school.repository;


import br.com.school.hexagono.domain.Course;

public interface CourseRepository {

    void delete(Course course);
    Course findById(Long id);
}
