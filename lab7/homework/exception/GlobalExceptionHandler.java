package lab7.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)

    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public String handleMissingParams(MissingServletRequestParameterException e) {
        return "Lipseste parametrul: " + e.getParameterName();
    }

    @ExceptionHandler(Exception.class)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception e) {
        if (e.getClass().getName().contains("springdoc") ||
                e.getClass().getName().contains("swagger")) {
            throw new RuntimeException(e);
        }
        return "A aparut o eroare: " + e.getMessage();
    }
}