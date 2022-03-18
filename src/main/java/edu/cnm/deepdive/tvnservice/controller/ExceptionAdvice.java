package edu.cnm.deepdive.tvnservice.controller;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *Provides a better experience to the user, by handling the way exceptions are displayed.
 */
@RestControllerAdvice
public class ExceptionAdvice {

  /**
   *  Handles the HTTP not found exception by displaying a descriptive message to the user.
   */
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "organization or opportunity not found")
  public void resourceNotFound(){
  }

  /**
   *  Handles the HTTP bad request exception by displaying a descriptive message to the user.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request content")
  public void badRequest(){
  }
}
