## ADDED Requirements

### Requirement: Filter repositories by owner
The `repoPageQuery` API MUST allow filtering the repository list by the creator (`ownerId`).

#### Scenario: Owner ID provided
- **WHEN** the `POST /repository/repoPageQuery` endpoint is called with a valid `ownerId` parameter
- **THEN** the API returns only those repositories where the `create_by` field matches the provided `ownerId`

#### Scenario: Owner ID not provided
- **WHEN** the `POST /repository/repoPageQuery` endpoint is called without an `ownerId` parameter
- **THEN** the API returns repositories without filtering by creator

### Requirement: Include creator name in response
The repository list response MUST include the human-readable name of the creator for each repository.

#### Scenario: Creator exists
- **WHEN** a repository is returned in the `repoPageQuery` response
- **THEN** it includes a `creatorName` field containing the `user_name` of the user who created it

#### Scenario: Creator is deleted or missing
- **WHEN** a repository is returned but the creator user record has `is_delete = 1` or is missing
- **THEN** the `creatorName` field MAY be null or empty, but the repository record is still returned
