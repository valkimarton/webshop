package com.bmeonlab.valki.webshop.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

    @ExceptionHandler({WebshopException.class})
    protected ResponseEntity<Object> handleWebshopException(RuntimeException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.BAD_REQUEST, request);   // TODO: create more exceptions, and add handlers for each.
    }

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.NOT_FOUND, request);
    }

    /*@ExceptionHandler({ServiceException.class, RepositoryException.class})
    protected ResponseEntity<Object> handleInternalError(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }*/
}
