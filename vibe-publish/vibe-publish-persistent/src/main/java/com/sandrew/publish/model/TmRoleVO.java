package com.sandrew.publish.model;

import com.sandrew.publish.core.bean.VO;
import lombok.Data;

@Data
public class TmRoleVO implements VO
{

    private Integer roleId;

    private String roleCode;

    private String roleName;

    private String roleType;

    private String roleStatus;

    private static final long serialVersionUID = 1L;

}