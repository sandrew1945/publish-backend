package com.sandrew.publish.model;

import java.io.Serializable;
import java.util.Date;

/**
 * MyBatis Generator model for tr_repo_user (Repo-User collaborator relation).
 * 
 * @mbg.generated
 */
public class TrRepoUserPO implements Serializable {

    private Integer id;
    private Integer repoId;
    private Integer userId;
    /** Delete tag: IF_TYPE_YES / IF_TYPE_NO */
    private Integer isDelete;
    private Integer createBy;
    private Date createDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
