package com.aurrix.phonebook.person.exception;

import com.aurrix.phonebook.person.exception.definition.PersonNotFoundException;
import com.aurrix.phonebook.person.exception.model.ExceptionResponse;
import com.aurrix.phonebook.person.phone.exceptions.definition.PhoneNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Service
public class BookExceptionHandler extends ResponseEntityExceptionHandler {

  public ExceptionResponse response(Exception e) {
    List<String> result = new ArrayList<>();
    if (e.getMessage() != null) result.add(e.getMessage());
    else result.add(e.getStackTrace()[0].toString());
    for (var t : e.getSuppressed()) {
      if (t.getMessage() != null) result.add(t.getMessage());
      else result.add(t.getStackTrace()[0].toString());
    }
    return new ExceptionResponse(result);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }
    return new ResponseEntity<>(response(ex), headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < ex.getFieldErrors().size(); i++) {
      result.add(
          ex.getFieldErrors().get(i).getField()
              + ":"
              + ex.getFieldErrors().get(i).getDefaultMessage());
    }
    return new ResponseEntity<>(new ExceptionResponse(result), headers, status);
  }

  @ExceptionHandler(PhoneNotFoundException.class)
  public ResponseEntity<Object> handleNotFoundPhone(PhoneNotFoundException e, WebRequest request) {
    return this.handleExceptionInternal(
        e, response(e), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(PersonNotFoundException.class)
  public ResponseEntity<Object> handleNotFoundPerson(
      PersonNotFoundException e, WebRequest request) {
    return this.handleExceptionInternal(
        e, response(e), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}
