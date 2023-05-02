package com.example.unclefood.proxy;


import com.example.unclefood.controller.dto.StudentDTO;
import com.example.unclefood.proxy.request.StudentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "${school.school.name}", path = "/student")
public interface ProxyStudentFeignClient {

	@GetMapping("getStudentByName")
	public ResponseEntity<StudentDTO>  findStudentByName(@RequestParam() String name);



}
