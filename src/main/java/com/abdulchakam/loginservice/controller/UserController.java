package com.abdulchakam.loginservice.controller;

import com.abdulchakam.loginservice.model.User;
import com.abdulchakam.loginservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        User response = userService.create(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/show")
    public ResponseEntity<List<User>> show() {
        List<User> response = userService.show();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User response = userService.update(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId) {
        userService.delete(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success deleted user");
    }
}
