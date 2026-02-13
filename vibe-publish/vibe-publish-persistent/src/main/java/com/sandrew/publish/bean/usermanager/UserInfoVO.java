package com.sandrew.publish.bean.usermanager;

import com.sandrew.publish.dictionary.Fixcode;
import com.sandrew.publish.model.TmRoleVO;
import com.sandrew.publish.core.annotation.EnumHandler;
import com.sandrew.publish.core.bean.VO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class UserInfoVO implements VO
{
    private Integer userId;
    private String userCode;
    private String userName;
    private String roleName;
    private String roleCode;
    private Date birthday;
    private String avatar;
    @EnumHandler(Fixcode.class)
    private String sex;
    private String phone;
    private String mobile;
    private String email;
    private List<TmRoleVO> roleList;
    @EnumHandler(Fixcode.class)
    private String userStatus;
    private String emailToken;
}
