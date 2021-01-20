package com.example.demo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDoFolder {
    private @Id @GeneratedValue Long id;
    private String description;
    
    ToDoFolder() {}

    ToDoFolder(String description) {
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        return true;
        if (!(o instanceof ToDoFolder))
        return false;
        ToDoFolder toDoFolder = (ToDoFolder) o;
        return Objects.equals(this.id, toDoFolder.id) && Objects.equals(this.description, toDoFolder.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description);
    }

    @Override
    public String toString() {
        return "To do item{" + "id=" + this.id + ", description='" + this.description + '\'' + '}';
    }
}
