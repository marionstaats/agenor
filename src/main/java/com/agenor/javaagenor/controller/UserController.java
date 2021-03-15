package com.agenor.javaagenor.controller;

import com.agenor.javaagenor.model.User;
import com.agenor.javaagenor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {

        try {
            User userData = userRepository.findByEmail(user.getEmail());

                if (user.getPassword().equals(userData.getPassword()) )
                    return new ResponseEntity<>(userData, HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
