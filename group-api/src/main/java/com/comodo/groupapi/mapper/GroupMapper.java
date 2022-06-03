package com.comodo.groupapi.mapper;

import com.comodo.groupapi.dto.CreateGroupDto;
import com.comodo.groupapi.dto.GroupDto;
import com.comodo.groupapi.dto.UpdateGroupDto;
import com.comodo.groupapi.entity.Group;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupDto dto);

    Group createDtotoEntity(CreateGroupDto createGroupDto);

    GroupDto toDto(Group todo);

    List<Group> toEntityList(List<GroupDto> todoDtoList);

    List<GroupDto> toDtoList(List<Group> todoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Group update(@MappingTarget Group todo, UpdateGroupDto updateGroupDto);

}
