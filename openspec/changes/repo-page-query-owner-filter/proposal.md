## Why

The `repoPageQuery` API currently only allows filtering by `repoName` and `status`, but does not support filtering by creator. Users need to view repositories filtered by owner (creator), and the response should display the creator's name to improve UI readability.

## What Changes

- Add `ownerId` (`Integer`) as an optional filter parameter to `POST /repository/repoPageQuery`, mapping to `tt_repo.create_by`
- Add `ownerId` field to `RepoDTO` (used as query condition)
- Update `RepositoryMapper.xml` `repoPageQuery` SQL to join `tm_user` on `create_by` and filter when `ownerId` is provided
- Add `creatorName` (`String`) field to `RepoBO` (populated from SQL join)
- Add `creatorName` (`String`) field to `RepoPageQueryVO` (returned to frontend)
- Update `RepositoryConvertor` mapping from `RepoBO` to `RepoPageQueryVO` to include `creatorName`
- Update `RepositoryController.repoPageQuery` to accept `ownerId` request param and pass to condition

## Capabilities

### New Capabilities

- `repo-page-query-owner-filter`: Filter repository list by owner/creator and display creator's name in results

### Modified Capabilities

<!-- No existing spec files exist yet; no delta specs needed -->

## Impact

- **API**: `POST /repository/repoPageQuery` — new optional `ownerId` param, new `creatorName` field in each result item
- **SQL**: `RepositoryMapper.xml` — additional LEFT JOIN on `tm_user` (aliased as `cu`) for `create_by`, new WHERE clause condition, new SELECT column
- **Java layers**: `RepoDTO`, `RepoBO`, `RepoPageQueryVO`, `RepositoryConvertor` in `vibe-publish-persistent`; `RepositoryController` in `vibe-publish-api`
- **No breaking changes** — `ownerId` is optional and `creatorName` is an additive field
