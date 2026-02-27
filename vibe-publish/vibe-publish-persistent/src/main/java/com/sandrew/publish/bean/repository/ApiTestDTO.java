package com.sandrew.publish.bean.repository;

import com.sandrew.publish.core.bean.DTO;
import lombok.Data;

import java.util.Map;

/**
 * DTO for testing an API endpoint.
 * Supports two test modes: Params (query string) and Body (JSON body).
 */
@Data
public class ApiTestDTO implements DTO {
    private Integer apiId;
    /**
     * Test type: "Params" for query-string parameters, "Body" for JSON request
     * body.
     * NOTE: Corresponds to the frontend tab selection.
     */
    private String testType;
    /** Key-value pairs for Params mode (appended to URL as query string) */
    private Map<String, String> params;
    /** Raw JSON string for Body mode */
    private String body;
}
