package br.com.school.controller.dto;

import br.com.school.hexagono.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private List<StudentDTO> studentList;

    public static CourseDTO build(Course course) {
        CourseDTO dto =new CourseDTO();

        List<StudentDTO> studentsDTOs = new ArrayList<>();
        StudentDTO studentsDTO = new StudentDTO();

        if (course.getStudentList() != null) {
            course.getStudentList().forEach(student -> studentsDTOs
                    .add(StudentDTO.builder()
                            .id(student.getId())
                            .name(student.getName())
                            .build()));

            dto.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .studentList(studentsDTOs)
                    .build();
        }else{
            dto.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .build();
        }

        return dto;
    }
}
