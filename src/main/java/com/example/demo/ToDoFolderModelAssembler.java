package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ToDoFolderModelAssembler implements RepresentationModelAssembler<ToDoFolder, EntityModel<ToDoFolder>> {

  @Override
  public EntityModel<ToDoFolder> toModel(ToDoFolder todofolder) {

    return EntityModel.of(todofolder, //
        linkTo(methodOn(ToDoFolderController.class).one(todofolder.getId())).withSelfRel(),
        linkTo(methodOn(ToDoFolderController.class).all()).withRel("todofolder"));
  }
}