## 1. Dependency Setup

- [ ] 1.1 Replace legacy `springfox-swagger2` and `springfox-swagger-ui` entries in the parent POM's `dependencyManagement` with `springdoc-openapi-starter-webmvc-ui` (v2.6.0+), and update the version property
- [ ] 1.2 Add `springdoc-openapi-starter-webmvc-ui` dependency (without version) to `vibe-publish-api/pom.xml`

## 2. Configuration

- [ ] 2.1 Create `SwaggerConfig.java` in `com.sandrew.publish.config` with an `OpenAPI` bean defining title ("Vibe Publish API"), version, description, and an API key security scheme for the `sid` header
- [ ] 2.2 Add Swagger paths (`/swagger-ui/**`, `/swagger-ui.html`, `/v3/api-docs/**`, `/v3/api-docs.yaml`) as `anon` entries in `ShiroConfiguration.filterChainDefinitionMapping` before the catch-all rule

## 3. Controller Annotations — LoginController

- [ ] 3.1 Add `@Tag(name = "Login", description = "...")` to `LoginController`
- [ ] 3.2 Add `@Operation` annotations to all 6 endpoints: `login`, `userInfo`, `setCurrentlyRole`, `validateToken`, `getMenuByRole`, `logout`

## 4. Controller Annotations — UserManagerController

- [ ] 4.1 Add `@Tag(name = "User Management", description = "...")` to `UserManagerController`
- [ ] 4.2 Add `@Operation` annotations to all 14 endpoints: `userManagerPageQuery`, `getUserInfoById`, `createUserInfo`, `createUserInfoForJsonBody`, `updateUserInfo`, `deleteUserInfo`, `deleteUserInfoByPathVariable`, `queryRelationRoles`, `deleteRoleRelation`, `queryUnRelationRoles`, `createRelation`, `getUserList`, `updatePassword`, `userValidate`, `getUserListByRoleType`

## 5. Controller Annotations — MenuManagerController

- [ ] 5.1 Add `@Tag(name = "Menu Management", description = "...")` to `MenuManagerController`
- [ ] 5.2 Add `@Operation` annotations to all 4 endpoints: `getMenuTree`, `createMenu`, `updateMenu`, `deleteMenu`

## 6. Controller Annotations — RoleManagerController

- [ ] 6.1 Add `@Tag(name = "Role Management", description = "...")` to `RoleManagerController`
- [ ] 6.2 Add `@Operation` annotations to all 8 endpoints: `roleManagerPageQuery`, `getRoleInfoById`, `createRole`, `updateRole`, `deleteRole`, `saveSelectedFunc`, `getCheckedPremission`, `roleValidate`

## 7. Controller Annotations — SecurityController

- [ ] 7.1 Add `@Tag(name = "Security", description = "...")` to `SecurityController`
- [ ] 7.2 Add `@Operation` annotation to `getLoginUser` endpoint

## 8. Model Annotations

- [ ] 8.1 Add `@Schema` annotations to key DTOs/VOs: `UserManagerDTO`, `UserInfoVO`, `UserPageQueryVO`, `FunctionsParam`, `JsonResult`

## 9. Verification

- [ ] 9.1 Run `mvn compile` to verify no compilation errors
- [ ] 9.2 Start the application and verify Swagger UI loads at `/swagger-ui/index.html`
- [ ] 9.3 Verify all endpoints are visible and grouped correctly by tag
- [ ] 9.4 Verify Swagger UI is accessible without authentication
