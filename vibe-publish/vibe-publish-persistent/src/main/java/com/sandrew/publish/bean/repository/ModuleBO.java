package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.BO;
import lombok.Data;

import java.util.Date;

/**
 * Module BO returned from service layer.
 */
@Data
public class ModuleBO implements BO {
    private Integer moduleId;
    private Integer repoId;
    private String moduleName;
    private String moduleDesc;
    private String urlBase;
    /** Raw Fixcode: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;
}
