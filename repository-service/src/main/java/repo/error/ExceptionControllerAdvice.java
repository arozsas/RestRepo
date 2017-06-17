package repo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import repo.RepositoryError;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RepositoryError> handleException(Exception e) {
        RepositoryError error = new RepositoryError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        return new ResponseEntity<RepositoryError>(error, HttpStatus.OK);
    }
}
