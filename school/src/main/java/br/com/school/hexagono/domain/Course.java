package br.com.school.hexagono.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class Course {
    private Long id;
    private String name;
    private Set<Student> studentList = new HashSet<>();

    public Course(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public boolean validEnroll(){
        if(studentList.size()<50){
            return  true;
        }else{
            log.error("{}: {}", BusinessException.Error.ERR001.getMessage());
            throw new BusinessException(BusinessException.Error.ERR001);
        }
    }
}
