## ADDED Requirements

### Requirement: SpringDoc OpenAPI dependency
The system SHALL include `springdoc-openapi-starter-webmvc-ui` as a dependency managed in the parent POM's `dependencyManagement` and referenced in the `vibe-publish-api` module only. The legacy `springfox-swagger2` and `springfox-swagger-ui` entries SHALL be removed from the parent POM.

#### Scenario: Application starts with SpringDoc
- **WHEN** the application starts
- **THEN** SpringDoc auto-configuration SHALL be active and no springfox-related errors occur

#### Scenario: Swagger UI is accessible
- **WHEN** a user navigates to `/swagger-ui/index.html`
- **THEN** the Swagger UI page SHALL load and display the API documentation

#### Scenario: OpenAPI JSON is accessible
- **WHEN** a client requests `/v3/api-docs`
- **THEN** the server SHALL return a valid OpenAPI 3.0 JSON document describing all endpoints

### Requirement: OpenAPI metadata configuration
The system SHALL provide a `SwaggerConfig` configuration class that defines an `OpenAPI` bean with project title, version, description, and a security scheme for the Shiro session token (`sid` header).

#### Scenario: API info is displayed
- **WHEN** a user views the Swagger UI
- **THEN** the page SHALL display the project title ("Vibe Publish API"), version, and description

#### Scenario: Security scheme is available
- **WHEN** a user clicks the "Authorize" button in Swagger UI
- **THEN** the dialog SHALL show an API key input for the `sid` header

### Requirement: Shiro whitelist for Swagger paths
The Shiro filter chain in `ShiroConfiguration` SHALL allow unauthenticated access to Swagger-related paths: `/swagger-ui/**`, `/swagger-ui.html`, `/v3/api-docs/**`, and `/v3/api-docs.yaml`.

#### Scenario: Unauthenticated access to Swagger UI
- **WHEN** an unauthenticated user navigates to `/swagger-ui/index.html`
- **THEN** the request SHALL NOT be intercepted by Shiro authentication filters and the page SHALL load normally

#### Scenario: Unauthenticated access to API docs
- **WHEN** an unauthenticated client requests `/v3/api-docs`
- **THEN** the server SHALL return the OpenAPI JSON without requiring a session

### Requirement: Controller-level API grouping
Each controller class SHALL be annotated with `@Tag(name = "...", description = "...")` to group its endpoints in Swagger UI by domain area.

#### Scenario: Controllers are grouped in Swagger UI
- **WHEN** a user views the Swagger UI
- **THEN** endpoints SHALL be grouped under tags: "Login", "User Management", "Menu Management", "Role Management", "Security"

### Requirement: Endpoint-level documentation
Each controller method SHALL be annotated with `@Operation(summary = "...", description = "...")` providing a concise summary and a descriptive explanation of the endpoint's purpose.

#### Scenario: Endpoint has summary and description
- **WHEN** a user expands an endpoint in Swagger UI
- **THEN** the endpoint SHALL display a summary line and a detailed description

#### Scenario: All endpoints are documented
- **WHEN** a user views the Swagger UI
- **THEN** every endpoint across all 5 controllers (~33 total) SHALL have an `@Operation` annotation with a non-empty summary

### Requirement: Request/response model documentation
Key DTOs, VOs, and parameter objects used in controller method signatures SHALL be annotated with `@Schema` to provide field-level descriptions in the generated documentation.

#### Scenario: Request body model is documented
- **WHEN** a user views an endpoint that accepts a DTO (e.g., `UserManagerDTO`)
- **THEN** the Swagger UI SHALL display a schema with field names and descriptions

#### Scenario: Response model is documented
- **WHEN** a user views an endpoint that returns a typed `JsonResult<T>`
- **THEN** the Swagger UI SHALL display the response schema structure
