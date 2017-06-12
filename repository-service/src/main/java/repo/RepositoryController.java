package repo;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Agnes_Rozsas
 */

@RestController
public class RepositoryController {

    private RepositoryManager repositoryManager = new RepositoryManager();

    @RequestMapping(value = "/repos", method = RequestMethod.POST)
    public Repository createRepository(@RequestBody Repository repository) {
        return repositoryManager.addRepository(repository);
    }

    @RequestMapping(value = "/repo", method = RequestMethod.DELETE)
    public String deleteRepository(@RequestParam("id") String id) {
        return repositoryManager.deleteRepository(id);
    }

    @RequestMapping(value = "/repos/getReposByCounter", method = RequestMethod.GET)
    public List<Repository> getRepoInfoByAccessCounter(@RequestParam("count") int count) {
        return repositoryManager.getRepoInfoByAccessCounter(count);
    }

    @RequestMapping(value = "/repo", method = RequestMethod.GET)
    public Repository getRepo(@RequestParam("id") String id) {
        return repositoryManager.getRepository(id);
    }
}
