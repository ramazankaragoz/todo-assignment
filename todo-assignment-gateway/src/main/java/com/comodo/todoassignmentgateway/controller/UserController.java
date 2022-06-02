package com.comodo.todoassignmentgateway.controller;


import com.comodo.todoassignmentgateway.client.UserClient;
import com.comodo.todoassignmentgateway.dto.UserDto;
import com.comodo.todoassignmentgateway.dto.UserInformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

   private final UserClient userClient;
   private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserClient userClient, BCryptPasswordEncoder passwordEncoder) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/information/{email}")
    @PreAuthorize("hasPermission('USER','READ')")
    public ResponseEntity<UserInformationDto> getInformation(@PathVariable("email") String email){
        return userClient.getInformation(email);
    }

    @PostMapping
    @PreAuthorize("hasPermission('USER','WRITE')")
    public ResponseEntity<String> save(@Validated @RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userClient.save(userDto);
    }

}
