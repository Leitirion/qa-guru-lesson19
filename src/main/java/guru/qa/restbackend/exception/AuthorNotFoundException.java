package guru.qa.restbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthorNotFoundException extends ResponseStatusException {

    public AuthorNotFoundException(HttpStatus status) {
        super(status);
    }
}
