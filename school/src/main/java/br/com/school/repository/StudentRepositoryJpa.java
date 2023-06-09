package br.com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface StudentRepositoryJpa extends JpaRepository <StudentEntity, Long> {

    @Query("select s from StudentEntity s where not exists elements(s.courses)")
    Set<StudentEntity> findStudentsWithoutCourse();

    @Query("select s from StudentEntity s where s.name LIKE %:name% ")
    StudentEntity findStudentByName(@Param("name") String name);

}


