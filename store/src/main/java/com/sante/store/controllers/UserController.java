package com.sante.store.controllers;

import com.sante.store.dtos.UserDto;
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
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(EntityToDto(userService.create(DtoToEntity(userDto))), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> showOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(EntityToDto(userService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/users/byEmail/{email}")
    public ResponseEntity<UserDto> getByEmail(@Valid @PathVariable("email") String email) {
        return new ResponseEntity<>(EntityToDto(userService.findByEmail(email)), HttpStatus.OK);
    }

    @PutMapping("/users/edit")
    public ResponseEntity<UserDto> edit(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(EntityToDto(userService.update(DtoToEntity(userDto))), HttpStatus.OK);
    }

    private User DtoToEntity(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user = userService.findById(userDto.getId());
        }
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        return user;
    }

    private UserDto EntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        return userDto;
    }
}
