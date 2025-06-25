package com.proyecto_byp.cl.proyecto_byp.assembler;

import com.proyecto_byp.cl.proyecto_byp.controller.v2.RegionControllerV2;
import com.proyecto_byp.cl.proyecto_byp.model.Region;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionAssembler implements RepresentationModelAssembler<Region, EntityModel<Region>> {

    @Override
    public EntityModel<Region> toModel(Region region) {
        return EntityModel.of(region,
            // enlace a self
            linkTo(methodOn(RegionControllerV2.class).getRegionById(region.getId())).withSelfRel(),
            // enlace a la colección
            linkTo(methodOn(RegionControllerV2.class).getAllRegion()).withRel("Obtener todas las regiones"),
            // enlace a actualizar (PUT)
            linkTo(methodOn(RegionControllerV2.class).updateRegion(region.getId(), region)).withRel("Actualizar regiones "),
            // enlace a eliminar (DELETE)
            linkTo(methodOn(RegionControllerV2.class).deleteRegion(region.getId())).withRel("Eliminar regiones"),
            // enlace a parcheo (PATCH)
            linkTo(methodOn(RegionControllerV2.class).patchRegion(region.getId(), region)).withRel("Actualizar parcial")
            
            );
            
    }
    
}