package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.DTO;
import lombok.Data;

import java.util.List;

/**
 * DTO for repository create / update operations.
 * repoId is null when creating, set when updating.
 */
@Data
public class RepoDTO implements DTO {
    private Integer repoId;
    private String repoName;
    private String repoDesc;
    /** Raw Fixcode: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
    private Integer ownerId;
    /** List of collaborating developer user IDs */
    private List<Integer> collaboratorIds;
}
