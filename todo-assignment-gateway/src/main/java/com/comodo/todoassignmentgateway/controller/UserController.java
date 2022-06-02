package com.comodo.todoassignmentgateway.controller;


import com.comodo.todoassignmentgateway.client.UserClient;
import com.comodo.todoassignmentgateway.dto.UserInformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

   private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/information/{email}")
    @PreAuthorize("hasPermission('USER','READ')")
    public ResponseEntity<UserInformationDto> getInformation(@PathVariable("email") String email){
        return userClient.getInformation(email);
    }

}
