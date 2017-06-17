package repo.error;

public class RepositoryConflictException extends Throwable {

    private String repositoryId;

    public RepositoryConflictException(final String id) {
        super(id);
        repositoryId = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }
}
