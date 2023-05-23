package com.example.schooluser.resources;

import com.example.schooluser.entities.User;
import com.example.schooluser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = userRepository.findById(id).get();
        return new ResponseEntity<User>(obj,HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail2(@RequestParam String email) {
        User obj = userRepository.findByEmail(email);
        return ResponseEntity.ok(obj);
    }
}
