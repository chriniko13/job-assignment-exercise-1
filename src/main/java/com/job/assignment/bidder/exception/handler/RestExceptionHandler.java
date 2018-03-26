package com.job.assignment.bidder.exception.handler;

import com.job.assignment.bidder.exception.ServiceException;
import com.job.assignment.bidder.exception.error.RestErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleInvalidRequest(ServiceException e, ServletWebRequest request) {
        RestErrorMessage error =
                new RestErrorMessage(e.getHttpStatus(),
                        e.getCode(),
                        e.getMessage(),
                        e.getDetailedMessage(),
                        e.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, e.getHttpStatus(), request);
    }

    /*
        Note: fallback exception handler.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInvalidRequest_Default(Exception e, ServletWebRequest request) {
        RestErrorMessage error =
                new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        null,
                        e.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
