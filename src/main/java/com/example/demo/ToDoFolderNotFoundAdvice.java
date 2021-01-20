package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ToDoFolderNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(ToDoFolderNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String toDoFolderNotFoundHandler(ToDoFolderNotFoundException ex) {
    return ex.getMessage();
  }
}