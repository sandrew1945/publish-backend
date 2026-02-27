package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.DTO;
import lombok.Data;

/**
 * DTO for module create / update operations.
 * moduleId is null when creating, set when updating.
 */
@Data
public class ModuleDTO implements DTO {
    private Integer moduleId;
    private Integer repoId;
    private String moduleName;
    private String moduleDesc;
    private String urlBase;
    /** Raw Fixcode: STATUS_ENABLE / STATUS_DISABLE */
    private Integer status;
}
