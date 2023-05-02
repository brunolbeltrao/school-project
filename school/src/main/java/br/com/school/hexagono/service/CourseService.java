package br.com.school.hexagono.service;

import br.com.school.controller.dto.CourseDTO;
import br.com.school.controller.dto.StudentDTO;
import br.com.school.hexagono.adapter.CourseRepositoryAdapter;
import br.com.school.hexagono.adapter.StudentRepositoryAdapter;
import br.com.school.hexagono.domain.Course;
import br.com.school.hexagono.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepositoryAdapter courseRepositoryAdapter;

    @Autowired
    private StudentRepositoryAdapter studentRepositoryAdapter;

    public CourseDTO save(CourseDTO dto) {
        Course course = Course.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
        return CourseDTO.build(courseRepositoryAdapter.save(course));
    }

    public void delete(Long id) {
        Course course = courseRepositoryAdapter.findCourseById(id);
        courseRepositoryAdapter.delete(course);
    }
    public CourseDTO enroll(Long studentid, Long courseId) {
        Student student = studentRepositoryAdapter.findStudentById(studentid);
        Course course = courseRepositoryAdapter.findCourseById(courseId);

        course.validEnroll();
        student.validEnroll();

        return courseRepositoryAdapter.enroll(student,course);
    }

    public List<CourseDTO> findCourseByStudent(Long courseId) {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        Student student = studentRepositoryAdapter.findStudentById(courseId);

        student.getCourseSet().stream().forEach(course -> {
            courseDTOs.add(CourseDTO.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .build());
        });
        return courseDTOs;

    }

    public List<CourseDTO> CoursesWithoutStudents() {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        Set<Course> courses = courseRepositoryAdapter.findCoursesWithoutStudents();

        courses.stream().forEach(course -> {
            courseDTOs.add(CourseDTO.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .build());
        });
        return courseDTOs;
    }

    public List<CourseDTO> Courses() {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        List<Course> courses = courseRepositoryAdapter.findCourses();

        courses.stream().forEach(course -> {
            courseDTOs.add(CourseDTO.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .build());
        });
        return courseDTOs;
    }
}
