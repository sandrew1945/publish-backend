package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.BO;
import lombok.Data;

import java.util.Date;

/**
 * API Endpoint BO returned from service layer.
 */
@Data
public class ApiBO implements BO {
    private Integer apiId;
    private Integer repoId;
    private Integer moduleId;
    private String apiName;
    private String apiPath;
    /** Raw Fixcode: INVOKE_TYPE_GET / POST / PUT / DELETE / etc. */
    private Integer invokeType;
    /** Raw Fixcode: STATUS_CODE_200 / 404 / etc. */
    private Integer statusCode;
    /** Raw Fixcode: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    /** Raw Fixcode: IF_TYPE_YES / IF_TYPE_NO */
    private Integer testable;
    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;
}
