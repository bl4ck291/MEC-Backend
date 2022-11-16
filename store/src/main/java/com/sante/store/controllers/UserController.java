package com.sante.store.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
     private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        User user = userService.saveUser(DtoToEntity(userDto));
        userService.addRoleToUser(user.getEmail(), "ROLE_CUSTOMER");
        return new ResponseEntity<>(EntityToDto(user), HttpStatus.CREATED);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                User user = userService.getUser(email);
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                //response.sendError(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/users/byEmail/{email}")
    public ResponseEntity<UserDto> getByEmail(@Valid @PathVariable("email") String email) {
        return new ResponseEntity<>(EntityToDto(userService.getUser(email)), HttpStatus.OK);
    }

    @PutMapping("/users/edit")
    public ResponseEntity<UserDto> edit(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(EntityToDto(userService.saveUser(DtoToEntity(userDto))), HttpStatus.OK);
    }

//    @PostMapping("/roles/save")
//    public ResponseEntity<Role> saveRole(@Valid @RequestBody Role role) {
//        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
//    }

    @GetMapping("/seller/users")
    public ResponseEntity<Page<User>> getUsers(Pageable request) {
        return new ResponseEntity<>(userService.getUsers(request), HttpStatus.OK);
    }

    @PostMapping("/seller/users/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getEmail(), form.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
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
