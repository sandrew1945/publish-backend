package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.BO;
import lombok.Data;

/**
 * Parameter BO returned from service layer.
 */
@Data
public class ParamBO implements BO {
    private Integer paramId;
    private Integer apiId;
    private String paramName;
    /** Required: true=yes, false=no */
    private Boolean paramRequire;
    /** Data type code string, e.g. "DATA_TYPE_STRING" — stored as varchar in DB */
    private String dataType;
    private String initVal;
    private String description;
    private Integer fatherId;
    /** Raw Fixcode: PARAM_TYPE_IN / OUT / COMMON / HEADER */
    private Integer paramType;
}
