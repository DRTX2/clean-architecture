package drtx.cleanArchitecture.example.infrastructure.web.exception;


import drtx.cleanArchitecture.example.domain.exception.EmailAlreadyExistsException;
import drtx.cleanArchitecture.example.domain.exception.InvalidUserDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEmailExists(EmailAlreadyExistsException ex) {
        return new ErrorResponse("EMAIL_ALREADY_EXISTS", ex.getMessage());
    }

    @ExceptionHandler(InvalidUserDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidData(InvalidUserDataException ex) {
        return new ErrorResponse("INVALID_USER_DATA", ex.getMessage());
    }
}