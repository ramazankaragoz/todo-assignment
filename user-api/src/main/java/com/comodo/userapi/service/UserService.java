package com.comodo.userapi.service;

import com.comodo.userapi.dto.UserInformationDto;

public interface UserService {

    UserInformationDto getUserInformation(String email);
}
