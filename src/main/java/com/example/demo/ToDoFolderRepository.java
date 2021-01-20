package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoFolderRepository extends JpaRepository<ToDoFolder, Long> {

}