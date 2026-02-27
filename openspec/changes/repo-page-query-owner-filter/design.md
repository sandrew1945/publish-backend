## Context

The repository list in the UI currently shows basic information but lacks the creator's name. It only stores `create_by` (an internal `user_id`) in `tt_repo`. Furthermore, the `repoPageQuery` API does not allow filtering repositories by their creator (`ownerId`). As part of improving repository management visibility, users should be able to filter repositories by owner and see the creator's name on each repository card/row. This design extends the current setup to support these requirements.

## Goals / Non-Goals

**Goals:**
- Enable filtering the repository list by `ownerId` through the existing `POST /repository/repoPageQuery` endpoint.
- Include the creator's human-readable name (`creatorName`) in the pagination response to avoid additional UI lookups.

**Non-Goals:**
- Modifying repository creation logic or changing how the owner is assigned.
- Implementing UI changes in this specific backend change context.

## Decisions

### 1. Augment `tt_repo` Query with Owner Details
**Decision**: Modify `RepositoryMapper.xml` `repoPageQuery` to include a `LEFT JOIN` on `tm_user` specifically for the `create_by` field to retrieve `user_name` as `creatorName`.

**Rationale**: `repoPageQuery` already utilizes complex joins to aggregate collaborators (`tr_repo_user`). Adding a new join to `tm_user` for the owner is consistent with this pattern and allows both filtering by `ownerId` and retrieving `creatorName` in a single query. The join will include the condition `is_delete = 0` to ensure it only relates to one active user record.

**Alternative**: Fetching owner names in Java memory by iterating over `RepoBO` elements post-SQL execution.
*Why rejected*: Less performant due to N+1 query potential or requiring batch user lookups. Pure SQL join is more efficient given `create_by` is indexed and directly linked to `tt_repo`.

### 2. DTO and VO Additions
**Decision**: 
- Add `ownerId` to `RepoDTO` for the incoming search filter.
- Add `creatorName` to `RepoBO` and `RepoPageQueryVO` for returning the mapped owner name.
- Explicitly update `RepositoryConvertor` to propagate this field.

**Rationale**: Retains the standard Layered Architecture pattern (Controller -> Service -> Persistent/Mapper). It clearly demarcates input conditions (DTO) from database results (BO) and client-facing presentations (VO).

## Risks / Trade-offs

- **[Risk] Slower SQL Query with additional JOIN** → Mitigation: `tm_user.user_id` and `tt_repo.create_by` should be indexed. Furthermore, `repoPageQuery` is paginated, bounding the Cartesian expansion.
- **[Risk] `LEFT JOIN` on `tm_user` returning multiple rows if not careful** → Mitigation: `tm_user` is authoritative and a given `create_by` maps to exactly one user row (with `is_delete = 0`). The query structure maintains `GROUP BY r.repo_id`.

## Migration Plan

No database schema migration is required. The `tt_repo.create_by` already accurately reflects the owner at creation time. Deployment is a standard drop-in replacement of backend jars.

## Open Questions

None at this time.
