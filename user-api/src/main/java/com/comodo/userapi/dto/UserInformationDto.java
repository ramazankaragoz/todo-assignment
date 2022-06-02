package com.comodo.userapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInformationDto implements Serializable {
    private static final long serialVersionUID = 6721570996184009996L;

    private String email;
    private String password;
    private Boolean enabled;
    private List<String> privileges;

}
