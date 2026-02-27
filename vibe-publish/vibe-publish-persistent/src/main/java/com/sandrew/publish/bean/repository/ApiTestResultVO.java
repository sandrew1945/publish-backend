package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.VO;
import lombok.Builder;
import lombok.Data;

/**
 * VO for API endpoint test result returned to frontend.
 */
@Data
@Builder
public class ApiTestResultVO implements VO {
    private Integer statusCode;
    private String responseBody;
    private Long elapsedMs;
}
