package br.com.school.controller;

import br.com.school.controller.dto.CourseDTO;
import br.com.school.controller.dto.StudentDTO;
import br.com.school.hexagono.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity <CourseDTO> create(@RequestBody CourseDTO vo){
        return new ResponseEntity<>(courseService.save(vo), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity <CourseDTO> update(@RequestBody CourseDTO vo) {
        CourseDTO courseDTO = courseService.save(vo);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("enroll")
    public ResponseEntity <CourseDTO> enroll(@RequestParam Long studentid,@RequestParam Long courseId){
        return new ResponseEntity<>(courseService.enroll(studentid,courseId), HttpStatus.CREATED);
    }

    @GetMapping("getCoursesByStudent")
    public ResponseEntity<CourseDTO>  findStudentsByCourse(@RequestParam() Long courseId){
        return new ResponseEntity(courseService.findCourseByStudent(courseId), HttpStatus.OK);
    }

    @GetMapping("getCoursesWithoutStudents")
    public ResponseEntity<CourseDTO>  findCoursesWithoutStudents(){
        return new ResponseEntity(courseService.CoursesWithoutStudents(), HttpStatus.OK);
    }

    @GetMapping("getCourses")
    public ResponseEntity<CourseDTO>  findCourses(){
        return new ResponseEntity(courseService.Courses(), HttpStatus.OK);
    }


}
