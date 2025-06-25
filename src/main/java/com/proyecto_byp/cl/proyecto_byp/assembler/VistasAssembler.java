package com.proyecto_byp.cl.proyecto_byp.assembler;

import com.proyecto_byp.cl.proyecto_byp.controller.v2.VistasControllerV2;
import com.proyecto_byp.cl.proyecto_byp.model.Vistas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VistasAssembler implements RepresentationModelAssembler<Vistas, EntityModel<Vistas>> {

    @Override
    public EntityModel<Vistas> toModel(Vistas vista) {
        return EntityModel.of(
            vista,
            // enlace a self
            linkTo(methodOn(VistasControllerV2.class).getVistaById(vista.getId())).withSelfRel(),
            // enlace a la colección
            linkTo(methodOn(VistasControllerV2.class).getAllVistas()).withRel("vistas"),
            // enlace a actualizar (PUT)
            linkTo(methodOn(VistasControllerV2.class).updateVista(vista.getId(), vista)).withRel("actualizar"),
            // enlace a eliminar (DELETE)
            linkTo(methodOn(VistasControllerV2.class).deleteVista(vista.getId())).withRel("eliminar"),
            // enlace a parcheo (PATCH)
            linkTo(methodOn(VistasControllerV2.class).patchVista(vista.getId(), vista)).withRel("actualizar-parcial")
            );
    }
    
}