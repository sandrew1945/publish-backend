package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.VO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * VO for paginated module list returned to frontend.
 * Status kept as Integer (raw Fixcode).
 */
@Data
@Builder
public class ModulePageQueryVO implements VO {
    private Integer moduleId;
    private Integer repoId;
    private String moduleName;
    private String moduleDesc;
    private String urlBase;
    /** Raw Fixcode: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    private Integer createBy;
    private Date createDate;
}
