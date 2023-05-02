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
public class StudentService {
    @Autowired
    private StudentRepositoryAdapter studentRepositoryAdapter;

    @Autowired
    private CourseRepositoryAdapter courseRepositoryAdapter;

    public StudentDTO save(StudentDTO dto) {
        Student student = Student.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
        return StudentDTO.build(studentRepositoryAdapter.save(student));
    }

    public void delete(Long id) {
        Student student = studentRepositoryAdapter.findStudentById(id);
        studentRepositoryAdapter.delete(student);
    }

    public List<StudentDTO> findStudentsByCourse(Long courseId){
        List<StudentDTO> studentDTOs = new ArrayList<>();
        Course course = courseRepositoryAdapter.findCourseById(courseId);

        course.getStudentList().stream().forEach(student -> {
            studentDTOs.add(StudentDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .build());
        });
        return studentDTOs;
    }


    public List<StudentDTO> StudentsWithoutCourse() {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        Set<Student> students = studentRepositoryAdapter.findStudentsWithoutCourse();

        students.stream().forEach(student -> {
            studentDTOs.add(StudentDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .build());
        });
        return studentDTOs;
    }

    public List<StudentDTO> findStudents() {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        List<Student> students = studentRepositoryAdapter.findStudents();

        students.stream().forEach(student -> {
            studentDTOs.add(StudentDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .build());
        });
        return studentDTOs;
    }

    public StudentDTO findStudentByName(String name) {
        Student student = studentRepositoryAdapter.findStudentByName(name);
        StudentDTO studentDTO = new StudentDTO().build(student);
        return studentDTO;
    }
}
