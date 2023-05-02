package br.com.school.controller.dto;

import br.com.school.hexagono.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;

    public static StudentDTO build(Student student) {
        return  StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .build();
    }
}
