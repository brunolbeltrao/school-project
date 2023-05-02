package br.com.school.repository;

import br.com.school.hexagono.domain.Student;

public interface StudentRepository {
    public static final int MAXSITE = 100;
    void delete(Student student);
    Student findById(Long id);
}

