package com.sandrew.publish.controller;

import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.exception.JsonException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/security")
@Tag(name = "Security", description = "Security context and current user info")
public class SecurityController extends BaseController {

    @Operation(summary = "Get login user", description = "Retrieve the currently authenticated user's information from the session")
    @GetMapping(value = "/getLoginUser")
    public AclUserBean getLoginUserInfo() throws JsonException {
        try {
            AclUserBean loginUser = getLoginUser();
            // System.out.println(1/0);
            return loginUser;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
    }

}
