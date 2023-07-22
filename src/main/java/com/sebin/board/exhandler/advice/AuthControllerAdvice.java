package com.sebin.board.exhandler.advice;

import com.sebin.board.exception.DuplicateIdException;
import com.sebin.board.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.sebin.board.controller")
public class AuthControllerAdvice {
  @ResponseStatus(HttpStatus.LOCKED)
  @ExceptionHandler(DuplicateIdException.class)
  public ErrorResult duplicateIdExHandler(DuplicateIdException e) {
    log.error("[exceptionHandler] ex", e);
    return new ErrorResult("Duplicate", e.getMessage());
  }
}
