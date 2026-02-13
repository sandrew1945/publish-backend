package com.sandrew.publish.bean.usermanager;

import com.sandrew.publish.core.bean.DTO;
import lombok.Data;

import java.time.LocalDate;

/**
 * @ClassName UserManagerDTO
 * @Description
 * @Author summer
 * @Date 2023/2/24 16:09
 **/
@Data
public class UserManagerDTO implements DTO
{
    private Integer userId;
    private String userCode;
    private String userName;
    private String password;
    private Integer sex;
    private String phone;
    private String mobile;
    private String email;
    private LocalDate birthday;
    private String avatar;
    private Integer userType;
    private Integer userStatus;
    private String emailToken;
}
