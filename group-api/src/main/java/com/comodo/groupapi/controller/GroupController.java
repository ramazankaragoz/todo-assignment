package com.comodo.groupapi.controller;

import com.comodo.groupapi.dto.CreateGroupDto;
import com.comodo.groupapi.dto.GroupDto;
import com.comodo.groupapi.dto.UpdateGroupDto;
import com.comodo.groupapi.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<GroupDto> save(@Valid @RequestBody CreateGroupDto createGroup) {
        return new ResponseEntity<>(groupService.save(createGroup), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @Valid @RequestBody UpdateGroupDto updateGroupDto) {
        return new ResponseEntity<>(groupService.update(id, updateGroupDto),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
