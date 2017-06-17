package repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.error.RepositoryConflictException;
import repo.error.RepositoryNotFoundException;

import java.util.List;

@RestController
public class RepositoryController {

    @Autowired
    private RepositoryManager repositoryManager;

    @RequestMapping(value = "/repo", method = RequestMethod.POST)
    public ResponseEntity<?> createRepository(@RequestBody Repository repository) throws RepositoryConflictException {
        ResponseEntity<?> responseEntity;
        if (!repositoryManager.repositoryExists(repository)) {
            repositoryManager.addRepository(repository);
            responseEntity = new ResponseEntity<Repository>(repository, HttpStatus.OK);
        } else {
            throw new RepositoryConflictException(repository.getId());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/repo", method = RequestMethod.GET)
    public ResponseEntity<?> getRepo(@RequestParam("id") String id) throws RepositoryNotFoundException {
        ResponseEntity<?> responseEntity;
        Repository repository = repositoryManager.getRepository(id);
        if (repository != null) {
            responseEntity = new ResponseEntity<Repository>(repository, HttpStatus.OK);
        } else {
            throw new RepositoryNotFoundException(id);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/repo", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRepository(@RequestParam("id") String id) throws RepositoryNotFoundException {
        ResponseEntity<?> responseEntity;
        Repository repository = repositoryManager.deleteRepository(id);
        if (repository != null) {
            responseEntity = new ResponseEntity<Repository>(HttpStatus.OK);
        } else {
            throw new RepositoryNotFoundException(id);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/repo/getReposByCounter", method = RequestMethod.GET)
    public ResponseEntity<List<Repository>> getRepoInfoByAccessCounter(@RequestParam("count") int count) {
        return new ResponseEntity<List<Repository>>(repositoryManager.getRepoInfoByAccessCounter(count), HttpStatus.OK);
    }

    @ExceptionHandler(RepositoryConflictException.class)
    public ResponseEntity<RepositoryError> handleRepositoryConflictException(RepositoryConflictException e) {
        RepositoryError error = new RepositoryError(HttpStatus.CONFLICT.toString(), "Repository with id: " + e.getRepositoryId() + " already exists!");
        return new ResponseEntity<RepositoryError>(error, HttpStatus.OK);
    }

    @ExceptionHandler(RepositoryNotFoundException.class)
    public ResponseEntity<RepositoryError> handleRepositoryNotFoundException(RepositoryNotFoundException e) {
        RepositoryError error = new RepositoryError(HttpStatus.NOT_FOUND.toString(), "Repository with id: " + e.getRepositoryId() + " does not exist!");
        return new ResponseEntity<RepositoryError>(error, HttpStatus.OK);
    }

}
