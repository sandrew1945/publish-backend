package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.VO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * VO for paginated repository list returned to frontend.
 * Status kept as Integer (raw Fixcode) — frontend handles display decoding.
 */
@Data
@Builder
public class RepoPageQueryVO implements VO {
    private Integer repoId;
    private String repoName;
    private String repoDesc;
    /** Raw Fixcode: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    private Integer createBy;
    private Date createDate;
    /** Comma-separated names of collaborating developers */
    private String collaboratorNames;
    /** Comma-separated developer user IDs from GROUP_CONCAT */
    private String collaboratorIdsString;
    /** Output from JOIN tm_user on create_by */
    private String creatorName;
}
