package com.example.schooloauth.resources;

import com.example.schooloauth.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.schooloauth.services.UserService;

@RestController
@RequestMapping(value = "/oauth")
public class OAuthResource {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        try{
            User user = userService.findByEmail(email);
            return ResponseEntity.ok(user);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.findByEmail("nina");
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
