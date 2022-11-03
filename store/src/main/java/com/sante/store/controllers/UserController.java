package com.sante.store.controllers;

import com.sante.store.entities.User;
import com.sante.store.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

     private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<User> getProfile(@Valid @PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/users/edit")
    public ResponseEntity<User> edit(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }
}
