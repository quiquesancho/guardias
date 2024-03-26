package com.edu.quique.controllers.error;

import com.edu.quique.api.model.ErrorType;
import com.edu.quique.application.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GuardiasExceptionControllerAdvice {

  @ExceptionHandler(TeacherNotFoundException.class)
  public ResponseEntity<ErrorType> handleTeacherNotFoundException(TeacherNotFoundException ex) {
    log.error("TeacherNotFoundException: {}", ex.getMessage());
    ErrorType errorType = new ErrorType();
    errorType = errorType.code(HttpStatus.NOT_FOUND.value()).description(ex.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(errorType);
  }

  @ExceptionHandler(ErrorUpdateXMLException.class)
  public ResponseEntity<ErrorType> handleErrorUpdateXMLException(ErrorUpdateXMLException ex) {
    log.error("TeacherNotFoundException: {}", ex.getMessage());
    ErrorType errorType = new ErrorType();
    errorType = errorType.code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ex.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(errorType);
  }

  @ExceptionHandler(AbsenceAlreadyExistsException.class)
  public ResponseEntity<ErrorType> handelAbsenceAlreadyExistsException(AbsenceAlreadyExistsException ex) {
    log.error("AbsenceAlreadyExistsException: {}", ex.getMessage());
    ErrorType errorType = new ErrorType();
    errorType = errorType.code(HttpStatus.BAD_REQUEST.value()).description(ex.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(errorType);
  }

  @ExceptionHandler(AbsenceCannotBeModifiedOrDeletedException.class)
  public ResponseEntity<ErrorType> handleAbsenceCannotBeModifiedException(AbsenceCannotBeModifiedOrDeletedException ex) {
    log.error("AbsenceAlreadyInCurseException: {}", ex.getMessage());
    ErrorType errorType = new ErrorType();
    errorType = errorType.code(HttpStatus.BAD_REQUEST.value()).description(ex.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(errorType);
  }

  @ExceptionHandler(AbsenceNotFoundException.class)
  public ResponseEntity<ErrorType> handleAbsenceNotFoundException(AbsenceNotFoundException ex) {
    log.error("AbsenceNotFoundException: {}", ex.getMessage());
    ErrorType errorType = new ErrorType();
    errorType = errorType.code(HttpStatus.BAD_REQUEST.value()).description(ex.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(errorType);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorType> handleBadCredentialsException(BadCredentialsException ex) {
    log.error("BadCredentialsException: {}", ex.getMessage());
    ErrorType errorType = new ErrorType();
    errorType = errorType.code(HttpStatus.UNAUTHORIZED.value()).description(ex.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(errorType);
  }
}
