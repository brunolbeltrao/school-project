package br.com.school.hexagono.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    public static List<Integer> sort(List<Integer> arr){

        return arr.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    @Test
    void validEnrollFalse() {
        //given
        Student student = new Student(1L,"Bruno");
        Course course1 = new Course(1L,"Math");
        Course course2 = new Course(2L,"Geography");
        Course course3 = new Course(3L,"English");
        Course course4 = new Course(4L,"IT");
        Course course5 = new Course(5L,"Philosophy");
        Course course6 = new Course(5L,"JUnit");
        Set<Course> courses = new HashSet<>();

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        student.setCourseSet(courses);

        //when
        Executable executable = () -> student.validEnroll();

        //then
        assertThrows(BusinessException.class,executable);

    }

}