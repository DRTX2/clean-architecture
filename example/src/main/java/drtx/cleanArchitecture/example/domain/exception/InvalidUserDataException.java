package drtx.cleanArchitecture.example.domain.exception;

public class InvalidUserDataException extends RuntimeException{
    public InvalidUserDataException(String message) {
        super(message);
    }
}
