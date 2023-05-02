package br.com.school.controller;

import br.com.school.controller.dto.StudentDTO;
import br.com.school.exceptions.UnsupportedGivenOperationException;
import br.com.school.hexagono.service.StudentService;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private Environment env;
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity <StudentDTO> create(@RequestBody StudentDTO vo){
        return new ResponseEntity<>(studentService.save(vo), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity <StudentDTO> update(@RequestBody StudentDTO vo) {
        StudentDTO studentDTO = studentService.save(vo);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getStudentsByCourse")
    public ResponseEntity<StudentDTO>  findStudentsByCourse(@RequestParam() Long courseId){
        return new ResponseEntity(studentService.findStudentsByCourse(courseId), HttpStatus.OK);
    }

    @GetMapping("getStudentsWithoutCourse")
    public ResponseEntity<StudentDTO>  findStudentsWithoutCourse(){
        return new ResponseEntity(studentService.StudentsWithoutCourse(), HttpStatus.OK);
    }

    @GetMapping("getStudents")
    public ResponseEntity<StudentDTO>  findStudents(){
        return new ResponseEntity(studentService.findStudents(), HttpStatus.OK);
    }

    @GetMapping("getStudentByName")
    public ResponseEntity<StudentDTO>  findStudentByName(@RequestParam() String name){
        logger.info("PORT = "+ env.getProperty("local.server.port"));
        return new ResponseEntity(studentService.findStudentByName(name), HttpStatus.OK);
    }
}
