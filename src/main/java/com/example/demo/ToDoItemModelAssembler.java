package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ToDoItemModelAssembler implements RepresentationModelAssembler<ToDoItem, EntityModel<ToDoItem>> {

  @Override
  public EntityModel<ToDoItem> toModel(ToDoItem todoitem) {

    return EntityModel.of(todoitem, //
        linkTo(methodOn(ToDoItemController.class).one(todoitem.getId())).withSelfRel(),
        linkTo(methodOn(ToDoItemController.class).all()).withRel("todofolder"));
  }
}