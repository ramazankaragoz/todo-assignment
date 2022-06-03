package com.comodo.groupapi.service;

import com.comodo.groupapi.dto.CreateGroupDto;
import com.comodo.groupapi.dto.GroupDto;
import com.comodo.groupapi.dto.UpdateGroupDto;

public interface GroupService {

    GroupDto save(CreateGroupDto createGroupDto);

    GroupDto update(Long id, UpdateGroupDto updateGroupDto);

    void delete(Long id);
}
