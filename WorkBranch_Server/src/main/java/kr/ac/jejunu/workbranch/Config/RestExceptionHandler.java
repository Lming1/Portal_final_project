package kr.ac.jejunu.workbranch.Config;

import kr.ac.jejunu.workbranch.Model.ApiResponseMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiResponseMessage(HttpStatus.BAD_REQUEST, error, 400, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiResponseMessage apiResponseMessage) {
        return new ResponseEntity<>(apiResponseMessage, apiResponseMessage.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage(NOT_FOUND);
        apiResponseMessage.setMessage(ex.getMessage());
        return buildResponseEntity(apiResponseMessage);
    }

//    @ExceptionHandler(Entityex)

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleExceptionInternal(ex, body, headers, status, request);
//        String error = "Server Error";
//        return buildResponseEntity(new ApiResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, error, 500, ex));
//    }
}
