package com.sandrew.publish.model;

import java.io.Serializable;
import java.util.Date;

/**
 * MyBatis Generator model for tt_api (Endpoint).
 * 
 * @mbg.generated
 */
public class TtApiPO implements Serializable {

    private Integer apiId;
    private Integer repoId;
    private Integer moduleId;
    private String apiName;
    private String apiPath;
    /** HTTP method: INVOKE_TYPE_GET / POST / PUT / DELETE / etc. */
    private Integer invokeType;
    /** e.g. STATUS_CODE_200 */
    private Integer statusCode;
    /** Status: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    /** Delete tag: IF_TYPE_YES / IF_TYPE_NO */
    private Integer isDelete;
    /** Testable: IF_TYPE_YES / IF_TYPE_NO */
    private Integer testable;
    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public Integer getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(Integer invokeType) {
        this.invokeType = invokeType;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getTestable() {
        return testable;
    }

    public void setTestable(Integer testable) {
        this.testable = testable;
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
