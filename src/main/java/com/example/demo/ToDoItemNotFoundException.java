package com.example.demo;


class ToDoItemNotFoundException extends RuntimeException {
    ToDoItemNotFoundException(Long id) {
          super("Could not find to do item " + id);
    }
  }