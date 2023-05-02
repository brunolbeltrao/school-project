package br.com.school.hexagono.adapter;

import br.com.school.hexagono.domain.Course;
import br.com.school.hexagono.domain.Student;
import br.com.school.repository.CourseEntity;
import br.com.school.repository.StudentEntity;
import br.com.school.repository.StudentRepository;
import br.com.school.repository.StudentRepositoryJpa;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public class StudentRepositoryAdapter implements StudentRepository {

    private final StudentRepositoryJpa studentRepositoryJpa;

    protected StudentRepositoryAdapter(StudentRepositoryJpa studentRepositoryJpa) {
        this.studentRepositoryJpa = studentRepositoryJpa;
    }

    public Student save(Student student) {
        StudentEntity studentEntity = new StudentEntity(student.getId(),student.getName());
        studentRepositoryJpa.save(studentEntity);
        return student;
    }

    public void delete(Student Student) {
        studentRepositoryJpa.deleteById(Student.getId());
    }

    @Override
    public Student findById(Long id) {
        StudentEntity studentEntity = studentRepositoryJpa.findById(id).get();
        Student student = build(studentEntity);
        return student;
    }

    private Student build(StudentEntity studentEntity) {
        Student student = Student.builder().id(studentEntity
                        .getId())
                .name(studentEntity.getName())
                .courseSet(buildListCourse(studentEntity.getCourses()))
                .build();

        return student;
    }

    private Set<Course> buildListCourse(Set<CourseEntity> courseEntitys) {
        Set<Course> courses =  new HashSet<>();

        courseEntitys.stream().forEach(courseEntity -> {
            Course course = new Course(courseEntity.getId(),courseEntity.getName());
            courses.add(course);
        });

        return courses;
    }

    private Set<Student> buildSetStudents(Set<StudentEntity> studentEntitys) {
        Set<Student> students =  new HashSet<>();

        studentEntitys.stream().forEach(studentEntity -> {
            Student student = new Student(studentEntity.getId(),studentEntity.getName());
            students.add(student);
        });

        return students;
    }

    private List<Student> buildListStudents(List<StudentEntity> studentEntitys) {
        List<Student> students =  new ArrayList<>();

        studentEntitys.stream().forEach(studentEntity -> {
            Student student = new Student(studentEntity.getId(),studentEntity.getName());
            students.add(student);
        });

        return students;
    }


    public Student findStudentById(Long id) {
        StudentEntity studentEntity = studentRepositoryJpa.findById(id).get();
        return build(studentEntity);
    }

    public Set<Student> findStudentsWithoutCourse() {
        return buildSetStudents(studentRepositoryJpa.findStudentsWithoutCourse());
    }

    public List<Student> findStudents() {
        return buildListStudents(studentRepositoryJpa.findAll());
    }

    public Student findStudentByName(String name) {
        StudentEntity studentEntity = studentRepositoryJpa.findStudentByName(name);
        return build(studentEntity);
    }
}
