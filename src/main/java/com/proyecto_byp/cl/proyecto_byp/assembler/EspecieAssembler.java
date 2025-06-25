package com.proyecto_byp.cl.proyecto_byp.assembler;


import com.proyecto_byp.cl.proyecto_byp.controller.v2.EspecieControllerV2;
import com.proyecto_byp.cl.proyecto_byp.model.Especie;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EspecieAssembler implements RepresentationModelAssembler<Especie, EntityModel<Especie>> {

    @Override
    public EntityModel<Especie> toModel(Especie especie) {
        return EntityModel.of(especie,
            // enlace self
            linkTo(methodOn(EspecieControllerV2.class).getEspecieById(especie.getId())).withSelfRel(),
            // enlace colección
            linkTo(methodOn(EspecieControllerV2.class).getAllEspecies()).withRel("especies"),
            // enlace actualización (PUT)
            linkTo(methodOn(EspecieControllerV2.class).updateEspecie(especie.getId(), especie)
            ).withRel("actualizar"),
            // enlace eliminación (DELETE)
            linkTo(methodOn(EspecieControllerV2.class).deleteEspecie(especie.getId())).withRel("eliminar"),
            // enlace parcheo (PATCH)
            linkTo(methodOn(EspecieControllerV2.class).patchEspecie(especie.getId(), especie)).withRel("actualizar-parcial")
        );
    }
    // toCollectionModel(...) lo hereda por defecto de la interfaz
}