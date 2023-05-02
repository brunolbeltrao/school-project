package br.com.school.hexagono.domain;

import br.com.school.repository.StudentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void validEnrollFalse() {
        //given
        Course course = new Course(1L,"test1");
        Set<Student> studentList = new HashSet<>();

        //when
        for (int i = 0; i < 51; i++) {
            Student student = new Student(Long.valueOf(i),"Student"+i);
            studentList.add(student);
        }
        course.setStudentList(studentList);

        Executable executable = () ->  course.validEnroll();

        //then
        assertThrows(BusinessException.class,executable);
    }
}