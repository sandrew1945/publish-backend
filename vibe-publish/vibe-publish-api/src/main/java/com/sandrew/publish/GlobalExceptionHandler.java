package com.sandrew.publish;

import com.sandrew.publish.core.exception.BaseException;
import com.sandrew.publish.core.result.JsonResult;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理，在本项目中因为由网关对异常进行处理，所以关闭该异常处理逻辑
 * @Author summer
 * @Date 2023/12/13 15:59
 **/
@RestControllerAdvice
public class GlobalExceptionHandler
{
    /**
     * 处理数据绑定异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ServletRequestBindingException.class)
    public JsonResult errorBindingHandler(HttpServletResponse response, Exception ex)
    {
        JsonResult result = new JsonResult();
        result.requestFailure(ex.getMessage());
        return result;
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public JsonResult noAuthorization(HttpServletResponse response, Exception ex)
    {
        JsonResult result = new JsonResult();
        result.requestFailure("您无权访问该资源，请联系系统管理员");
        return result;
    }

    /**
     * 处理请求动作异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public JsonResult errorActionHandler(HttpServletResponse response, Exception ex)
    {
        JsonResult result = new JsonResult();
        while (null != ex)
        {
            Throwable throwable = ex.getCause();
            if (null == throwable)
            {
                break;
            }
            ex = (Exception) throwable;
        }
        result.requestFailure(ex.getMessage());
        return result;
    }

    /**
     * 未知异常处理
     *
     * @param
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public JsonResult unknowExceptionHandler(HttpServletResponse response, Exception ex)
    {
        JsonResult result = new JsonResult();
        result.requestFailure(ex.getMessage());
        return result;
    }
}
