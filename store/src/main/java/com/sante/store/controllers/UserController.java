package com.sante.store.controllers;

import com.sante.store.dtos.UserDto;
import com.sante.store.entities.Role;
import com.sante.store.entities.User;
import com.sante.store.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
     private final UserService userService;

     @GetMapping("/users")
     public ResponseEntity<Page<User>> getUsers(Pageable request) {
         return new ResponseEntity<>(userService.getUsers(request), HttpStatus.OK);
     }

    @PostMapping("/users/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(EntityToDto(userService.saveUser(DtoToEntity(userDto))), HttpStatus.CREATED);
    }

    @PostMapping("/roles/save")
    public ResponseEntity<Role> saveRole(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @PostMapping("/users/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getEmail(), form.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/users/byEmail/{email}")
    public ResponseEntity<UserDto> getByEmail(@Valid @PathVariable("email") String email) {
        return new ResponseEntity<>(EntityToDto(userService.getUser(email)), HttpStatus.OK);
    }

    @PutMapping("/users/edit")
    public ResponseEntity<UserDto> edit(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(EntityToDto(userService.saveUser(DtoToEntity(userDto))), HttpStatus.OK);
    }

    private User DtoToEntity(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user = userService.getUser(userDto.getEmail());
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
@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}
