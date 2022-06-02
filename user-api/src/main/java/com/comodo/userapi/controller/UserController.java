package com.comodo.userapi.controller;

import com.comodo.userapi.dto.UserInformationDto;
import com.comodo.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/information/{email}")
    public ResponseEntity<UserInformationDto> getInformation(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.getUserInformation(email), HttpStatus.OK);
    }

}
