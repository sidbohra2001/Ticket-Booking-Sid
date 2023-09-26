package com.booking.city.exceptionshandler;

import com.booking.city.exceptions.AlreadyExistsException;
import com.booking.city.exceptions.InvalidIdException;
import com.booking.city.exceptions.NoDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<Format> handleInvalidIdException(InvalidIdException e) {
        return new ResponseEntity<>(Format.builder().status(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<Format> handleNoDataException(NoDataException e) {
        return new ResponseEntity<>(Format.builder().status(HttpStatus.NOT_FOUND.name()).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Format> handleAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(Format.builder().status(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Format> handleRestClientException(RestClientException e) {
        return new ResponseEntity<>(Format.builder().status(HttpStatus.SERVICE_UNAVAILABLE.name()).message(e.getMessage()).build(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Format> handleException(RuntimeException e) {
        return new ResponseEntity<>(Format.builder().status(HttpStatus.SERVICE_UNAVAILABLE.name()).message(e.getMessage()).build(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
