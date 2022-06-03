package com.comodo.todoassignmentgateway.client;

import com.comodo.todoassignmentgateway.config.feign.FeignClientConfig;
import com.comodo.todoassignmentgateway.dto.CreateGroupDto;
import com.comodo.todoassignmentgateway.dto.GroupDto;
import com.comodo.todoassignmentgateway.dto.UpdateGroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "group-client", url = "http://localhost:8083/group",configuration = {FeignClientConfig.class})
public interface GroupClient {

    @PostMapping
    ResponseEntity<GroupDto> save(@Valid @RequestBody CreateGroupDto createGroup);

    @PutMapping("{id}")
    ResponseEntity<GroupDto> update(@PathVariable Long id, @Valid @RequestBody UpdateGroupDto updateGroupDto);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
