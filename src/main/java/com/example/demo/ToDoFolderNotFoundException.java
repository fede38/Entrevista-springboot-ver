package com.example.demo;


class ToDoFolderNotFoundException extends RuntimeException {
    ToDoFolderNotFoundException(Long id) {
          super("Could not find to do folder " + id);
    }
  }