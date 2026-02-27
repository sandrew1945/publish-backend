package com.sandrew.publish.model;

import java.io.Serializable;
import java.util.Date;

/**
 * MyBatis Generator model for tt_param (API Parameter).
 * 
 * @mbg.generated
 */
public class TtParamPO implements Serializable {

    private Integer paramId;
    private Integer apiId;
    private String paramName;
    /** Required: 0=no, 1=yes */
    private Boolean paramRequire;
    /** Data type: DATA_TYPE_STRING / NUMBER / BOOLEAN / etc. */
    private String dataType;
    private String initVal;
    private String description;
    private Integer fatherId;
    /** Param type: PARAM_TYPE_IN / OUT / COMMON / HEADER */
    private Integer paramType;
    /** Delete tag: IF_TYPE_YES / IF_TYPE_NO */
    private Integer isDelete;
    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Boolean getParamRequire() {
        return paramRequire;
    }

    public void setParamRequire(Boolean paramRequire) {
        this.paramRequire = paramRequire;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getInitVal() {
        return initVal;
    }

    public void setInitVal(String initVal) {
        this.initVal = initVal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
