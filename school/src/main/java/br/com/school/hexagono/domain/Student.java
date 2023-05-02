package br.com.school.hexagono.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class Student {
    private Long id;
    private String name;
    private Set<Course> courseSet = new HashSet<>();

    public Student(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public boolean validEnroll() {
        if(courseSet.size()<5){
            return  true;
        }else{
            log.error("{}: {}", BusinessException.Error.ERR002.getMessage());
            throw new BusinessException(BusinessException.Error.ERR002);
        }
    }
}
