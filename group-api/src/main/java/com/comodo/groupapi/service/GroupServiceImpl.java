package com.comodo.groupapi.service;

import com.comodo.groupapi.dto.CreateGroupDto;
import com.comodo.groupapi.dto.GroupDto;
import com.comodo.groupapi.dto.UpdateGroupDto;
import com.comodo.groupapi.exception.GroupNotFoundException;
import com.comodo.groupapi.mapper.GroupMapper;
import com.comodo.groupapi.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDto save(CreateGroupDto createGroupDto) {
        var group = groupMapper.createDtotoEntity(createGroupDto);

        var savedGroup = groupRepository.save(group);

        return groupMapper.toDto(savedGroup);
    }

    @Override
    public GroupDto update(Long id, UpdateGroupDto updateGroupDto) {
        var group = groupRepository.getById(id);

        if (Objects.isNull(group)){
            throw new GroupNotFoundException("Group not found.");
        }

        var updatedGroup = groupMapper.update(group, updateGroupDto);
        return groupMapper.toDto(groupRepository.save(updatedGroup));
    }

    @Override
    public void delete(Long id) {
        var group = groupRepository.getById(id);

        if (Objects.isNull(group)){
            throw new GroupNotFoundException("Group not found.");
        }
        groupRepository.deleteById(id);
    }
}
