package com.sandrew.publish.model;

import java.io.Serializable;
import java.util.Date;

/**
 * MyBatis Generator model for tt_repo (Repository).
 * 
 * @mbg.generated
 */
public class TtRepoPO implements Serializable {

    private Integer repoId;
    private String repoName;
    private String repoDesc;
    /** Status: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    /** Delete tag: IF_TYPE_YES / IF_TYPE_NO */
    private Integer isDelete;
    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoDesc() {
        return repoDesc;
    }

    public void setRepoDesc(String repoDesc) {
        this.repoDesc = repoDesc;
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
