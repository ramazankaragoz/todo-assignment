package com.comodo.todoassignmentgateway.client;

import com.comodo.todoassignmentgateway.config.feign.FeignClientConfig;
import com.comodo.todoassignmentgateway.dto.UserDto;
import com.comodo.todoassignmentgateway.dto.UserInformationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "user-client", url = "http://localhost:8081/user",configuration = {FeignClientConfig.class})
public interface UserClient {

    @GetMapping("/information/{email}")
    ResponseEntity<UserInformationDto> getInformation(@PathVariable("email") String email);
    @PostMapping
    ResponseEntity<String> save(@Valid @RequestBody UserDto userDto);
}
