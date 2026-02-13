## Why

The project currently lacks interactive API documentation. Frontend developers must manually inspect controller source code or rely on ad-hoc communication to understand API contracts (endpoints, request parameters, response structures). Adding Swagger (OpenAPI) documentation provides an auto-generated, always-up-to-date API reference with a built-in test UI, significantly accelerating frontend development.

The existing `springfox-swagger2` v2.9.2 dependency in the parent POM is **incompatible with Spring Boot 3** and must be replaced with `springdoc-openapi`, the modern OpenAPI 3 solution for Spring Boot 3.

## What Changes

- **BREAKING**: Remove legacy `springfox-swagger2` and `springfox-swagger-ui` dependencies from parent POM
- Add `springdoc-openapi-starter-webmvc-ui` dependency for Spring Boot 3 compatibility
- Create a `SwaggerConfig` configuration class with OpenAPI metadata (title, version, description, security scheme)
- Add Swagger/OpenAPI annotations (`@Tag`, `@Operation`, `@Parameter`, `@Schema`) to all 5 controllers (~33 endpoints):
  - `LoginController` — 6 endpoints (login, logout, userInfo, validateToken, setCurrentlyRole, getMenuByRole)
  - `UserManagerController` — 14 endpoints (CRUD, pagination, role relations, password management)
  - `MenuManagerController` — 4 endpoints (tree query, create, update, delete)
  - `RoleManagerController` — 8 endpoints (CRUD, pagination, permission management)
  - `SecurityController` — 1 endpoint (getLoginUser)
- Add `@Schema` annotations to key DTOs/VOs/POs used as request/response models
- Whitelist Swagger UI paths (`/swagger-ui/**`, `/v3/api-docs/**`) in Shiro filter chain to allow unauthenticated access

## Capabilities

### New Capabilities
- `swagger-integration`: SpringDoc OpenAPI integration, configuration, Shiro whitelisting, and API annotation conventions

### Modified Capabilities
_None — no existing specs are affected._

## Impact

- **Dependencies**: Remove `springfox-swagger2` and `springfox-swagger-ui`; add `springdoc-openapi-starter-webmvc-ui`
- **Configuration**: New `SwaggerConfig.java` in `vibe-publish-api`; updated `ShiroConfiguration.java` filter chain
- **Controllers**: All 5 controllers receive OpenAPI annotations (additive, non-breaking)
- **Models**: Key DTOs/VOs/POs receive `@Schema` annotations (additive, non-breaking)
- **Runtime**: Swagger UI available at `/swagger-ui/index.html`; API docs at `/v3/api-docs`
