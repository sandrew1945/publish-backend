package com.sandrew.publish.bean.login;

import com.sandrew.publish.bean.usermanager.UserInfoVO;
import com.sandrew.publish.bean.usermanager.UserManagerBO;
import com.sandrew.publish.core.bean.AclUserBean;
import org.mapstruct.Mapper;

/**
 * @ClassName RoleManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface LoginConvertor
{
    LoginBO toLoginBO(AclUserBean aclUserBean);

    LoginVO toLoginVO(LoginBO loginBO);

    UserInfoVO toUserInfoVO(UserManagerBO userManagerBO);

}
