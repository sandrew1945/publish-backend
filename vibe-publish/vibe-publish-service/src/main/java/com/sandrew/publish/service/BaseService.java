package com.sandrew.publish.service;

import com.sandrew.publish.core.bean.AclUserBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import static com.sandrew.publish.dictionary.Constants.LOGIN_USER;

public interface BaseService
{
    default AclUserBean getLoginUser()
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        AclUserBean loginUser = (AclUserBean) session.getAttribute(LOGIN_USER);
        return loginUser;
    }
}
