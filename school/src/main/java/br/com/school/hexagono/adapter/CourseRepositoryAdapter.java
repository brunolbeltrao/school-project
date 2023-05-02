package br.com.school.hexagono.adapter;

import br.com.school.controller.dto.CourseDTO;
import br.com.school.controller.dto.StudentDTO;
import br.com.school.hexagono.domain.Course;
import br.com.school.hexagono.domain.Student;
import br.com.school.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseRepositoryJpa courseRepositoryJpa;
    private final StudentRepositoryJpa studentRepositoryJpa;

    public CourseRepositoryAdapter(CourseRepositoryJpa courseRepositoryJpa, StudentRepositoryJpa studentRepositoryJpa) {
        this.courseRepositoryJpa = courseRepositoryJpa;
        this.studentRepositoryJpa = studentRepositoryJpa;
    }

    public Course save(Course course) {
        CourseEntity courseEntity = new CourseEntity(course.getId(),course.getName());
        courseRepositoryJpa.save(courseEntity);
        return course;
    }

    public void delete(Course course) {
        courseRepositoryJpa.deleteById(course.getId());;
    }

    private Course build(CourseEntity courseEntity) {
        Course course = Course.builder()
                .id(courseEntity.getId())
                .name(courseEntity.getName())
                .studentList(buildListStudent(courseEntity.getStudentList()))
                .build();
        return course;
    }

    private Set<Student> buildListStudent(Set<StudentEntity> studentEntitys) {
        Set<Student> students =  new HashSet<>();

        studentEntitys.stream().forEach(studentEntity -> {
            Student student = new Student(studentEntity.getId(),studentEntity.getName());
            students.add(student);
        });

        return students;
    }

    private Set<StudentEntity> buildListStudentEntity(Set<Student> students) {
        Set<StudentEntity> studentEntitys =  new HashSet<>();

        students.stream().forEach(student -> {
            StudentEntity studentEntity = new StudentEntity(student.getId(),student.getName());
            studentEntitys.add(studentEntity);
        });

        return studentEntitys;
    }

    private Set<CourseEntity> buildListCourseEntity(Set<Course> courses) {
        Set<CourseEntity> courseEntitys =  new HashSet<>();

        courses.stream().forEach(course -> {
            CourseEntity courseEntity = new CourseEntity(course.getId(),course.getName());
            courseEntitys.add(courseEntity);
        });

        return courseEntitys;
    }

    private Set<Course> buildSetCourse(Set<CourseEntity> courseEntitys) {
        Set<Course> courses =  new HashSet<>();

        courseEntitys.stream().forEach(courseEntity -> {
            Course course = new Course(courseEntity.getId(),courseEntity.getName());
            courses.add(course);
        });

        return courses;
    }

    private List<Course> buildListCourse(List<CourseEntity> courseEntitys) {
        List<Course> courses =  new ArrayList<>();

        courseEntitys.stream().forEach(courseEntity -> {
            Course course = new Course(courseEntity.getId(),courseEntity.getName());
            courses.add(course);
        });

        return courses;
    }

    @Override
    public Course findById(Long id) {
        CourseEntity  courseEntity = courseRepositoryJpa.findById(id).get();
        Course course = build(courseEntity);
        return course;
    }

    public Course findCourseById(Long id) {
        CourseEntity courseEntity = courseRepositoryJpa.findById(id).get();
        Course course = build(courseEntity);
        return course;
    }

    public CourseDTO enroll(Student student, Course course) {

        CourseEntity courseEntity = new CourseEntity(course.getId(),course.getName(),buildListStudentEntity(course.getStudentList()));
        StudentEntity studentEntity = new StudentEntity(student.getId(),student.getName(),buildListCourseEntity(student.getCourseSet()));

        courseEntity.getStudentList().addAll(Arrays.asList(studentEntity));
        studentEntity.getCourses().add(courseEntity);

        courseRepositoryJpa.save(courseEntity);
        studentRepositoryJpa.save(studentEntity);

        StudentDTO studentDTO = StudentDTO.builder().id(studentEntity.getId()).name(studentEntity.getName()).build();

        return(CourseDTO.builder().id(courseEntity.getId()).name(courseEntity.getName()).studentList(Arrays.asList(studentDTO)).build());
    }

    public Set<Course> findCoursesWithoutStudents() {
        return buildSetCourse(courseRepositoryJpa.findCoursesWithoutStudents());
    }

    public List<Course> findCourses() {
        return buildListCourse(courseRepositoryJpa.findAll());
    }
}

