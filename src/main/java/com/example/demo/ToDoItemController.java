package com.example.demo;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
class ToDoItemController {
    private final ToDoItemRepository repository;
    private final ToDoItemModelAssembler assembler;

    ToDoItemController(ToDoItemRepository repository, ToDoItemModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/todoitems")
    CollectionModel<EntityModel<ToDoItem>> all() {
        List<EntityModel<ToDoItem>> todoitems = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(todoitems, linkTo(methodOn(ToDoItemController.class).all()).withSelfRel());
    }

    @PostMapping("/todoitems")
    ToDoItem newToDoItem(@RequestBody ToDoItem newToDoItem) {
        return repository.save(newToDoItem);
    }

    @GetMapping("/todoitems/{id}")
    EntityModel<ToDoItem> one(@PathVariable Long id) throws ToDoItemNotFoundException{
        ToDoItem todoitem = repository.findById(id)
            .orElseThrow(() -> new ToDoItemNotFoundException(id));
        return assembler.toModel(todoitem);
    }

    @PutMapping("/todoitems/{id}")
    ToDoItem replaceToDoItem(@RequestBody ToDoItem newToDoItem, @PathVariable Long id) {
        return repository.findById(id)
        .map(todoitem -> {
            todoitem.setDescription(newToDoItem.getDescription());
            todoitem.setChecked(newToDoItem.getChecked());
            return repository.save(todoitem);
        })
        .orElseGet(() -> {
            newToDoItem.setId(id);
            return repository.save(newToDoItem);
        });
    }

    @DeleteMapping("/todoitems/{id}")
    void deleteToDoItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}