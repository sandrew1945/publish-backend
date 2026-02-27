package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.BO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Repository BO returned from service layer.
 * Collaborators are loaded by joining tr_repo_user → tm_user.
 */
@Data
public class RepoBO implements BO {
    private Integer repoId;
    private String repoName;
    private String repoDesc;
    /** Raw Fixcode: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;
    /** Collaborating developer user IDs */
    private List<Integer> collaboratorIds;
    /** Collaborating developer user names (comma-separated from GROUP_CONCAT) */
    private String collaboratorNames;
    /** Comma-separated developer user IDs from GROUP_CONCAT */
    private String collaboratorIdsString;
    /** Output from JOIN tm_user on create_by */
    private String creatorName;
}
