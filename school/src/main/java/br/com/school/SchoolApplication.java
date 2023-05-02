package br.com.school;


import br.com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;


@SpringBootApplication
@EnableDiscoveryClient
public class SchoolApplication  implements CommandLineRunner  {

    @Autowired
    private CourseRepositoryJpa courseRepositoryJpa;
    @Autowired
    private StudentRepositoryJpa studentRepositoryJpa;
    @Override
    public void run(String... args) throws Exception {

        CourseEntity course1 = new CourseEntity(null,"Information systems");
        CourseEntity course2 = new CourseEntity(null,"Software Engineer");
        CourseEntity course3 = new CourseEntity(null,"Music");
        CourseEntity course4 = new CourseEntity(null,"Music");
        CourseEntity course5 = new CourseEntity(null,"Engineer");
        CourseEntity course6 = new CourseEntity(null,"Tourism");

        StudentEntity student1 = new StudentEntity(null,"Bruno Leite Beltrão");
        StudentEntity student2 = new StudentEntity(null,"Elon Musk");
        StudentEntity student3 = new StudentEntity(null,"Maria");
        StudentEntity student4 = new StudentEntity(null,"Bill");
        StudentEntity student5 = new StudentEntity(null,"Jon");

        course1.getStudentList().addAll(Arrays.asList(student1,student2));
        course2.getStudentList().addAll(Arrays.asList(student3,student4));

        student1.getCourses().add(course1);
        student2.getCourses().add(course1);
        student3.getCourses().add(course2);
        student4.getCourses().add(course2);

        courseRepositoryJpa.save(course1);
        courseRepositoryJpa.save(course2);
        courseRepositoryJpa.save(course3);
        courseRepositoryJpa.save(course4);
        courseRepositoryJpa.save(course5);
        courseRepositoryJpa.save(course6);
        studentRepositoryJpa.saveAll(Arrays.asList(student1,student2,student3,student4,student5));
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }
}
