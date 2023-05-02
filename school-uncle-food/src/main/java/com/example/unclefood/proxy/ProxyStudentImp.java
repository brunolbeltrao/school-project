package com.example.unclefood.proxy;

import com.example.unclefood.controller.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProxyStudentImp implements ProxyStudent {

    @Override
    public List<StudentDTO> getStudents(List<String> keys) {
        return null;
    }
}
