package com.example.demo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDoItem {
    private @Id @GeneratedValue Long id;
    private String description;
    private Boolean checked = false;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ToDoFolder folder;

    ToDoItem() {}

    ToDoItem(String description, ToDoFolder folder) {
        this.description = description;
        this.folder = folder;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        return true;
        if (!(o instanceof ToDoItem))
        return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return Objects.equals(this.id, toDoItem.id) && Objects.equals(this.description, toDoItem.description)
            && Objects.equals(this.checked, toDoItem.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.checked);
    }

    @Override
    public String toString() {
        return "To do item{" + "id=" + this.id + ", description='" + this.description + '\'' + ", checked='" + this.checked + '\'' + '}';
    }
}
