package repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepositoryController {

    @Autowired
    private RepositoryManager repositoryManager;

    @RequestMapping(value = "/repo", method = RequestMethod.POST)
    public ResponseEntity<?> createRepository(@RequestBody Repository repository) {
        ResponseEntity<?> responseEntity;
        if (!repositoryManager.repositoryExists(repository)) {
            repositoryManager.addRepository(repository);
            responseEntity = new ResponseEntity<Repository>(repository, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/repo", method = RequestMethod.GET)
    public ResponseEntity<?> getRepo(@RequestParam("id") String id) {
        ResponseEntity<?> responseEntity;
        Repository repository = repositoryManager.getRepository(id);
        if (repository != null) {
            responseEntity = new ResponseEntity<Repository>(repository, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

//    @RequestMapping(value = "/repo", method = RequestMethod.DELETE)
//    public String deleteRepository(@RequestParam("id") String id) {
//        return repositoryManager.deleteRepository(id);
//    }



    @RequestMapping(value = "/repos/getReposByCounter", method = RequestMethod.GET)
    public List<Repository> getRepoInfoByAccessCounter(@RequestParam("count") int count) {
        return repositoryManager.getRepoInfoByAccessCounter(count);
    }
}
