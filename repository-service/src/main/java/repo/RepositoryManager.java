package repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Agnes_Rozsas
 */

public class RepositoryManager {

    private Map<String, Repository> repositories = new HashMap<String, Repository>();

    public Repository getRepository(final String id) {
        Repository repository = repositories.get(id);
        repository.getCounter().incrementAndGet();
        return repositories.get(id);
    }

    public Repository addRepository(final Repository repository) {
        repositories.put(repository.getId(), repository);
        return repository;
    }

    public String deleteRepository(final String repoName) {
        Repository repository = repositories.remove(repoName);
        String msg = null;
        if (repository == null) {
            msg = "Repository does not exist: " + repoName;
        } else {
            msg = "Repository successfully deleted: " + repoName;
        }
        return msg;
    }

    public List<Repository> getRepoInfoByAccessCounter(final int accessCount) {
        return repositories.entrySet().stream().filter(stringRepositoryEntry -> stringRepositoryEntry.getValue().getAccessCounter() >= accessCount)
            .map(stringRepositoryEntry -> stringRepositoryEntry.getValue()).collect(Collectors.toList());
    }

    public String getInfo(final String repoName) {
        Repository repository = repositories.get(repoName);
        String msg = null;
        if (repository == null) {
            msg = "Repository does not exist: " + repoName;
        } else {
            msg = repository.toString();
        }
        return msg;
    }
}
