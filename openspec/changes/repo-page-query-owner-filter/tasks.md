## 1. Domain Models and DTOs

- [x] 1.1 Add `ownerId` (Integer) to `RepoDTO.java`
- [x] 1.2 Add `creatorName` (String) to `RepoBO.java`
- [x] 1.3 Add `creatorName` (String) to `RepoPageQueryVO.java`

## 2. SQL Mapper

- [x] 2.1 Update `RepositoryMapper.xml` `repoPageQuery` mapping:
  - Add `LEFT JOIN tm_user cu ON r.create_by = cu.user_id AND cu.is_delete = ${@com.sandrew.publish.dictionary.Fixcode@IF_TYPE_NO.getCode()}`
  - Add `cu.user_name AS creatorName` to the `SELECT` clause
  - Add `<if test="condition.ownerId != null">` block to filter `r.create_by = #{condition.ownerId}`

## 3. Service and Controller Layers

- [x] 3.1 Update `RepositoryConvertor.java` (if necessary, check mapstruct/custom mapping) to map `RepoBO.creatorName` to `RepoPageQueryVO.creatorName`
- [x] 3.2 Update `RepositoryController.java` `repoPageQuery` method signature to accept `@RequestParam(required = false) Integer ownerId`
- [x] 3.3 Set `ownerId` on `condition` object in `RepositoryController.java` before calling the service
