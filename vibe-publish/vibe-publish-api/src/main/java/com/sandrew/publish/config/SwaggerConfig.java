package com.sandrew.publish.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI configuration.
 * NOTE: Provides interactive API documentation at /swagger-ui/index.html
 * and supports Shiro session-based authentication via the "sid" header.
 */
@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "sid";

    @Bean
    public OpenAPI publishOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vibe Publish API")
                        .version("1.0")
                        .description("Backend API documentation for the Vibe Publish system. " +
                                "Use the Authorize button to set your Shiro session token (sid) for authenticated requests."))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("Shiro session token")));
    }
}
