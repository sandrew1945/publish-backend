package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.DTO;
import lombok.Data;

/**
 * DTO for a single parameter entry — used in bulk saveParams operation.
 */
@Data
public class ParamDTO implements DTO {
    private Integer paramId;
    private Integer apiId;
    private String paramName;
    private Boolean paramRequire;
    /** Data type, e.g. "DATA_TYPE_STRING" */
    private String dataType;
    private String initVal;
    private String description;
    private Integer fatherId;
    /** Raw Fixcode: PARAM_TYPE_IN / OUT / COMMON / HEADER */
    private Integer paramType;
}
