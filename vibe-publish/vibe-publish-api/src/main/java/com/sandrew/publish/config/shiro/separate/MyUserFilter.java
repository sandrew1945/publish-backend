package com.sandrew.publish.config.shiro.separate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandrew.publish.core.result.JsonResult;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.web.filter.authc.UserFilter;

/**
 * Created by summer on 2019/12/19.
 */
public class MyUserFilter extends UserFilter
{
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        // 这里也可以不用保存 保存当前request 可在登陆后重新请求当前 request
        this.saveRequest(request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json;charset=utf-8");
        JsonResult result = new JsonResult();
        result.requestFailure("The user is not logged in");
        ObjectMapper objectMapper = new ObjectMapper();
        httpResponse.getWriter().write(objectMapper.writeValueAsString(result));
        return false;
    }
}
