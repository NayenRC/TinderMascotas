package com.proyecto_byp.cl.proyecto_byp.controller.v2;

import com.proyecto_byp.cl.proyecto_byp.assembler.ComunaAssembler;



import com.proyecto_byp.cl.proyecto_byp.model.Comuna;
import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.service.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v2/comunas")

public class ComunaControllerV2 {

    @Autowired
    private ComunaService comunaService;
    @Autowired
    private ComunaAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Comuna>>> getAllComuna() {
    List<EntityModel<Comuna>> comuna = comunaService.findAll().stream().map(assembler::toModel)
        .collect(Collectors.toList());
    if(comuna.isEmpty()){
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(CollectionModel.of(
        comuna,
        linkTo(methodOn(ComunaControllerV2.class).getAllComuna()).withSelfRel()
    ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comuna>> getComunaById(@PathVariable Long id) {
        Comuna comuna = comunaService.findById(id);
        if (comuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(comuna));
    }


    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comuna>> createComuna(@RequestBody Comuna comuna) {
        Comuna newComuna = comunaService.save(comuna);
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComunaControllerV2.class) 
                .getComunaById(newComuna.getId())).toUri()).body(assembler.toModel(newComuna));
    }


    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comuna>> updateComuna(@PathVariable Long id, @RequestBody Comuna comuna) {
        comuna.setId(id);
        Comuna updatedComuna = comunaService.save(comuna);
        return ResponseEntity.ok(assembler.toModel(updatedComuna));
    }


    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comuna>> patchComuna(@PathVariable Long id, @RequestBody Comuna comunaParcial) {
        Comuna updatedComuna = comunaService.patchComuna(id, comunaParcial);
        if (updatedComuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedComuna));
    }


    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteComuna(@PathVariable Long id) {
        Comuna comuna = comunaService.findById(id);
        if (comuna == null) {
            return ResponseEntity.notFound().build();
        }
        comunaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}