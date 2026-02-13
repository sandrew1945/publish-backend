## Context

The project is a Spring Boot 3.3.2 / Java 17 multi-module Maven application (`vibe-publish`) with Shiro-based authentication. The parent POM currently declares `springfox-swagger2` v2.9.2, which is incompatible with Spring Boot 3 (Springfox has been abandoned since 2020). There are 5 controllers exposing ~33 REST endpoints with no API documentation.

The API module (`vibe-publish-api`) is the only module containing controllers and is where Swagger configuration and annotations will be concentrated.

## Goals / Non-Goals

**Goals:**
- Provide auto-generated, interactive API documentation accessible at `/swagger-ui/index.html`
- Annotate all existing endpoints with meaningful descriptions for frontend developers
- Ensure Swagger UI is accessible without authentication (whitelisted in Shiro)
- Define a group-based organization (by controller/domain area) for easy navigation

**Non-Goals:**
- Generating client SDKs from the OpenAPI spec (future consideration)
- Adding API versioning or restructuring existing endpoint paths
- Writing integration tests against the Swagger spec

## Decisions

### 1. SpringDoc OpenAPI over Springfox

**Decision:** Use `springdoc-openapi-starter-webmvc-ui` (v2.6.0+).

**Rationale:**
- Springfox has been abandoned since 2020 and is incompatible with Spring Boot 3 / Jakarta EE
- SpringDoc is the de-facto standard for Spring Boot 3, actively maintained, and supports OpenAPI 3.0/3.1
- Provides auto-detection of Spring MVC endpoints with zero-config defaults

**Alternatives considered:**
- *Keep Springfox*: Not viable — fails at startup with Spring Boot 3
- *Manual OpenAPI YAML*: High maintenance burden, diverges from actual code over time

### 2. Dependency version in parent POM, referenced in API module only

**Decision:** Declare `springdoc-openapi-starter-webmvc-ui` version in the parent POM's `dependencyManagement` section (replacing the legacy springfox entries), and add the dependency (without version) in `vibe-publish-api/pom.xml` only.

**Rationale:**
- Follows the project's existing Maven convention of centralizing version management in the parent POM
- Only the API module contains controllers and serves HTTP traffic, so only it needs the actual dependency
- Core, persistent, and service modules don't need Swagger
- The parent POM's legacy springfox entries will be replaced (they are not used by any module currently)

### 3. Annotation strategy — `@Tag` + `@Operation` on controllers

**Decision:** Use `@Tag` at the class level and `@Operation` at the method level. Use `@Parameter` for non-trivial parameters. Use `@Schema` on key DTOs/VOs.

**Rationale:**
- `@Tag` groups endpoints by controller domain (Login, User Management, etc.)
- `@Operation` provides summary/description that frontend developers can read directly in Swagger UI
- `@Schema` on models auto-generates request/response schemas, reducing manual documentation effort
- Avoid over-annotating — SpringDoc auto-detects most information from Spring MVC annotations

### 4. Shiro filter chain update

**Decision:** Add `/swagger-ui/**`, `/swagger-ui.html`, `/v3/api-docs/**`, and `/v3/api-docs.yaml` as `anon` entries in `ShiroConfiguration`.

**Rationale:**
- Swagger UI and API docs must be accessible without login for developer convenience
- These paths are static resources and API metadata — no security risk in exposing them
- Placed before the catch-all `/**` rule to ensure they are matched first

### 5. OpenAPI metadata configuration

**Decision:** Create a `SwaggerConfig` class with an `OpenAPI` bean defining title, version, description, and a security scheme for the Shiro session token (`sid` header).

**Rationale:**
- Allows frontend developers to see the required authentication header directly in Swagger UI
- The "Try it out" feature will include the `sid` header, enabling authenticated API testing from the docs

## Risks / Trade-offs

- **[Endpoint exposure in production]** → Swagger UI exposes all API details. Mitigation: can be disabled in production via `springdoc.swagger-ui.enabled=false` in `application.properties`. Not implementing this now (development-focused tool) but straightforward to add later.
- **[Annotation maintenance burden]** → Adding `@Operation` to ~33 endpoints is a one-time cost. Future endpoints should follow the same pattern. Mitigation: the convention is simple and well-documented.
- **[SpringDoc version compatibility]** → SpringDoc 2.x is aligned with Spring Boot 3.x. Mitigation: use a stable release (2.6.0+) and pin the version in the parent POM properties.
