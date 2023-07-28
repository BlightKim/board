package com.sebin.board.exhandler.advice;

import static java.util.stream.Collectors.*;

import com.sebin.board.exception.DuplicateIdException;
import com.sebin.board.exhandler.ErrorResult;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(basePackages = "com.sebin.board.controller")
public class AuthControllerAdvice {


  @ResponseStatus(HttpStatus.LOCKED)
  @ExceptionHandler(DuplicateIdException.class)
  public ErrorResult duplicateIdExHandler(DuplicateIdException e, HttpServletRequest request) {
    log.error("[exceptionHandler] ex", e);
    return ErrorResult.builder().timeStamp(getTimeStamp()).code(HttpStatus.LOCKED.value())
        .message(e.getMessage()).path(request.getServletPath()).build();
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResult methodArgumentExHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
    BindingResult bindingResult = e.getBindingResult();
    FieldError fieldError = bindingResult.getFieldError();
    String message = fieldError.getDefaultMessage();
    return ErrorResult.builder().timeStamp(getTimeStamp()).code(HttpStatus.BAD_REQUEST.value())
        .message(message).path(request.getServletPath()).build();
  }

  private String getTimeStamp() {
    return new Date().toString();
  }
}
