package com.proyecto_byp.cl.proyecto_byp.assembler;

import com.proyecto_byp.cl.proyecto_byp.controller.v2.ComunaControllerV2;
import com.proyecto_byp.cl.proyecto_byp.model.Comuna;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComunaAssembler implements RepresentationModelAssembler<Comuna, EntityModel<Comuna>> {

    @Override
    public EntityModel<Comuna> toModel(Comuna comuna) {
        return EntityModel.of(comuna,
            // enlace a self
            linkTo(methodOn(ComunaControllerV2.class).getComunaById(comuna.getId())).withSelfRel(),
            // enlace a la colección
            linkTo(methodOn(ComunaControllerV2.class).getAllComunas()).withRel("comunas"),
            // enlace a actualizar (PUT)
            linkTo(methodOn(ComunaControllerV2.class).updateComuna(comuna.getId(), comuna)).withRel("actualizar"),
            // enlace a eliminar (DELETE)
            linkTo(methodOn(ComunaControllerV2.class).deleteComuna(comuna.getId())).withRel("eliminar"),
            // enlace a parcheo (PATCH)
            linkTo(methodOn(ComunaControllerV2.class).patchComuna(comuna.getId(), comuna)).withRel("actualizar-parcial"));
    }
    // toCollectionModel() lo hereda por defecto de RepresentationModelAssembler
}