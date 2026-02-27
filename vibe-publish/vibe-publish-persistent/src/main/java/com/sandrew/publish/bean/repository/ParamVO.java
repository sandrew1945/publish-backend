package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.VO;
import lombok.Builder;
import lombok.Data;

/**
 * VO for parameter details returned to frontend.
 */
@Data
@Builder
public class ParamVO implements VO {
    private Integer paramId;
    private Integer apiId;
    private String paramName;
    private Boolean paramRequire;
    private String dataType;
    private String initVal;
    private String description;
    private Integer fatherId;
    /** Raw Fixcode: PARAM_TYPE_IN / OUT / COMMON / HEADER */
    private Integer paramType;
}
