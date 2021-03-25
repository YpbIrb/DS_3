package ru.nsu.smal.ds3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OSMExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(NodeNotFoundException.class)
    public ResponseEntity<String> handleNoComponentException(NodeNotFoundException noComponentException) {
        logger.error("EXCEPTION : ", noComponentException);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noComponentException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(Exception exception) {
        logger.error ("EXCEPTION : ", exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
