package com.comodo.userapi.service;

import com.comodo.userapi.dto.UserDto;
import com.comodo.userapi.dto.UserInformationDto;

public interface UserService {

    UserInformationDto getUserInformation(String email);
    void save(UserDto userDto);
}
