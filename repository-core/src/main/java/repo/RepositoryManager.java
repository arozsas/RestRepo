package repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryManager {

    private final Map<String, Repository> repositories = new HashMap<String, Repository>();

    public List<Repository> getRepositories() {
        return repositories.values().stream().collect(Collectors.toList());
    }

    public Repository getRepository(final String id) {
        Repository repository = repositories.get(id);
        if (repository != null) {
            repository.getCounter().incrementAndGet();
        }
        return repository;
    }

    public void addRepository(final Repository repository) {
        repositories.put(repository.getId(), repository);
    }

    public Repository deleteRepository(final String repoName) {
        return repositories.remove(repoName);
    }

    public List<Repository> getRepoInfoByAccessCounter(final int accessCount) {
        return repositories.entrySet().stream().filter(stringRepositoryEntry -> stringRepositoryEntry.getValue().getAccessCounter() >= accessCount)
                .map(stringRepositoryEntry -> stringRepositoryEntry.getValue()).collect(Collectors.toList());
    }

    public boolean repositoryExists(final Repository repository) {
        return repositories.containsKey(repository.getId());
    }
}
