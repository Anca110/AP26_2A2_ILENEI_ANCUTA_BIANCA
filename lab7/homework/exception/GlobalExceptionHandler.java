package lab7.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//clasa asta e un tratator global, lucreaa cu toate controller ele REST
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    //daca lipseste un parametru din request=> prinde eroarea aici

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //spune ce cod HTTP sa intorci
    //400 Bad Request adc cererea clientului e gresita, incompleta
    public String handleMissingParams(MissingServletRequestParameterException e) {
        return "Lipseste parametrul: " + e.getParameterName();
    }

    @ExceptionHandler(Exception.class)
    //daca apare orice alta exceptie o prinde metoda asta

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //500 Internal Server Error
    public String handleGeneralException(Exception e) {
        // daca eroarea vine de la Swagger, o lasam sa treaca normal
        if (e.getClass().getName().contains("springdoc") ||
                e.getClass().getName().contains("swagger")) {
            throw new RuntimeException(e);
        }
        return "A aparut o eroare: " + e.getMessage();
    }
}