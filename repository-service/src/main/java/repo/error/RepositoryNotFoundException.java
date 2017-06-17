package repo.error;

public class RepositoryNotFoundException extends Throwable {

    private String repositoryId;

    public RepositoryNotFoundException(final String id) {
        super(id);
        repositoryId = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }
}
