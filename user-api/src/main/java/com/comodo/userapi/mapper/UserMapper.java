package com.comodo.userapi.mapper;

import com.comodo.userapi.dto.UserDto;
import com.comodo.userapi.dto.UserInformationDto;
import com.comodo.userapi.entity.AppUser;
import com.comodo.userapi.entity.Privilege;
import com.comodo.userapi.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AppUser userDtoToAppUser(UserDto userDto);

    @Mapping(target = "privileges",expression = "java(mapToRolePrivileges(appUser))")
    UserInformationDto userToUserInformationDto(AppUser appUser);

    AppUser userInformationDtoToUser(UserInformationDto userInformationDto);


    default List<String> mapToRolePrivileges(AppUser appUser){
        Collection<Role> roles = appUser.getRoles();
        Set<Privilege> privilegeList=new HashSet<>();
        roles.forEach(role-> privilegeList.addAll(role.getPrivileges()));
        return privilegeList.stream().map(Privilege::getPrivilegeName).collect(Collectors.toList());
    }
}
