package com.proyecto_byp.cl.proyecto_byp.assembler;

import com.proyecto_byp.cl.proyecto_byp.controller.v2.RazaControllerV2;
import com.proyecto_byp.cl.proyecto_byp.model.Raza;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RazaAssembler implements RepresentationModelAssembler<Raza, EntityModel<Raza>> {

    @Override
    public EntityModel<Raza> toModel(Raza raza) {
        return EntityModel.of(
            raza,
            // enlace a self
            linkTo(methodOn(RazaControllerV2.class).getRazaById(raza.getId())).withSelfRel(),
            // enlace a la colección
            linkTo(methodOn(RazaControllerV2.class).getAllRazas()).withRel("razas"),
            // enlace a actualizar (PUT)
            linkTo(methodOn(RazaControllerV2.class).updateRaza(raza.getId(), raza)).withRel("actualizar"),
            // enlace a eliminar (DELETE)
            linkTo(methodOn(RazaControllerV2.class).deleteRaza(raza.getId())).withRel("eliminar"),
            // enlace a parcheo (PATCH)
            linkTo(methodOn(RazaControllerV2.class).patchRaza(raza.getId(), raza)).withRel("actualizar-parcial"));
    }
    // toCollectionModel(...) lo toma por defecto de la interfaz
}