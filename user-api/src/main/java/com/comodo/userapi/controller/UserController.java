package com.comodo.userapi.controller;

import com.comodo.userapi.dto.UserDto;
import com.comodo.userapi.dto.UserInformationDto;
import com.comodo.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody UserDto userDto){
        userService.save(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED.getReasonPhrase(),HttpStatus.CREATED);
    }

    @GetMapping("/information/{email}")
    public ResponseEntity<UserInformationDto> getInformation(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.getUserInformation(email), HttpStatus.OK);
    }

}
