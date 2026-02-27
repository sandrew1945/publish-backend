package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.VO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * VO for paginated API endpoint list returned to frontend.
 * All code fields kept as Integer (raw Fixcode).
 */
@Data
@Builder
public class ApiPageQueryVO implements VO {
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
}
