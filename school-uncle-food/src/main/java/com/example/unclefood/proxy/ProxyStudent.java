package com.example.unclefood.proxy;

import com.example.unclefood.controller.dto.StudentDTO;

import java.util.List;

public interface ProxyStudent {

    List<StudentDTO> getStudents(List<String> keys);

}
