package com.comodo.todoassignmentgateway.controller;


import com.comodo.todoassignmentgateway.client.UserClient;
import com.comodo.todoassignmentgateway.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

   private final UserClient userClient;
   private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserClient userClient, BCryptPasswordEncoder passwordEncoder) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Registers a new user to the system with default Admin Role")
    @PostMapping("/register")
    public ResponseEntity<String> save(@Valid @RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userClient.save(userDto);
    }

}
