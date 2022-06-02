package com.comodo.userapi.service;

import com.comodo.userapi.dto.UserInformationDto;
import com.comodo.userapi.entity.AppUser;
import com.comodo.userapi.mapper.UserMapper;
import com.comodo.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserInformationDto getUserInformation(String email) {

        AppUser appUser = userRepository.getByEmail(email);

        return userMapper.userToUserInformationDto(appUser);
    }
}
