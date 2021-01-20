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
class ToDoFolderController {
    private final ToDoFolderRepository repository;
    private final ToDoFolderModelAssembler assembler;

    ToDoFolderController(ToDoFolderRepository repository, ToDoFolderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/todofolders")
    CollectionModel<EntityModel<ToDoFolder>> all() {
        List<EntityModel<ToDoFolder>> todofolder = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(todofolder, linkTo(methodOn(ToDoFolderController.class).all()).withSelfRel());
    }

    @PostMapping("/todofolders")
    ToDoFolder newToDoFolder(@RequestBody ToDoFolder newToDoFolder) {
        return repository.save(newToDoFolder);
    }

    @GetMapping("/todofolders/{id}")
    EntityModel<ToDoFolder> one(@PathVariable Long id) throws ToDoFolderNotFoundException{
        ToDoFolder todofolder = repository.findById(id)
            .orElseThrow(() -> new ToDoFolderNotFoundException(id));
        return assembler.toModel(todofolder);
    }

    @PutMapping("/todofolders/{id}")
    ToDoFolder replaceToDoFolder(@RequestBody ToDoFolder newToDoFolder, @PathVariable Long id) {
        return repository.findById(id)
        .map(todofolder -> {
            todofolder.setDescription(newToDoFolder.getDescription());
            return repository.save(todofolder);
        })
        .orElseGet(() -> {
            newToDoFolder.setId(id);
            return repository.save(newToDoFolder);
        });
    }

    @DeleteMapping("/todofolders/{id}")
    void deleteToDoFolder(@PathVariable Long id) {
        repository.deleteById(id);
    }
}