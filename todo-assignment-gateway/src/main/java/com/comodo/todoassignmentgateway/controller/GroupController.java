package com.comodo.todoassignmentgateway.controller;

import com.comodo.todoassignmentgateway.client.GroupClient;
import com.comodo.todoassignmentgateway.dto.CreateGroupDto;
import com.comodo.todoassignmentgateway.dto.GroupDto;
import com.comodo.todoassignmentgateway.dto.UpdateGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/group")
public class GroupController {

    private final GroupClient groupClient;

    public GroupController(GroupClient groupClient) {
        this.groupClient = groupClient;
    }

    @Operation(summary = "Save a new group to the system")
    @PostMapping
    public ResponseEntity<GroupDto> save(@Valid @RequestBody CreateGroupDto createGroup) {
        return groupClient.save(createGroup);
    }

    @Operation(summary = "Partial update group to the system")
    @PutMapping("{id}")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @Valid @RequestBody UpdateGroupDto updateGroupDto) {
        return groupClient.update(id, updateGroupDto);
    }

    @Operation(summary = "Delete a group")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return groupClient.delete(id);
    }
}
