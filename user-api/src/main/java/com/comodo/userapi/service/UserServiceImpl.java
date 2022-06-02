package com.comodo.userapi.service;

import com.comodo.userapi.dto.UserDto;
import com.comodo.userapi.dto.UserInformationDto;
import com.comodo.userapi.entity.AppUser;
import com.comodo.userapi.entity.Role;
import com.comodo.userapi.exception.UserAlreadyExistException;
import com.comodo.userapi.mapper.UserMapper;
import com.comodo.userapi.repository.RoleRepository;
import com.comodo.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserInformationDto getUserInformation(String email) {

        AppUser appUser = userRepository.getByEmail(email);

        return userMapper.userToUserInformationDto(appUser);
    }

    @Override
    public void save(UserDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new UserAlreadyExistException("A User is already registered with this email address.");
        }

        AppUser appUser = userMapper.userDtoToAppUser(userDto);
        Role roleAdmin = roleRepository.getByRoleName("ROLE_ADMIN");
        appUser.setRoles(Arrays.asList(roleAdmin));
        userRepository.save(appUser);

    }
}
