package com.proyecto_byp.cl.proyecto_byp.controller.v2;

import com.proyecto_byp.cl.proyecto_byp.service.EspecieService;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_byp.cl.proyecto_byp.assembler.EspecieAssembler;
import com.proyecto_byp.cl.proyecto_byp.model.Especie;



@RestController

@RequestMapping("/api/v2/especies")

public class EspecieControllerV2 {
  @Autowired

  private EspecieService especieService;

  @Autowired
  private EspecieAssembler assembler;
  
  @GetMapping (produces=MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<CollectionModel<EntityModel<Especie>>> getAllEspecies() {
    List<EntityModel<Especie>> especies = especieService.findAll().stream().map(assembler::toModel)
        .collect(Collectors.toList());
    if(especies.isEmpty()){
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(CollectionModel.of(
        especies,
        linkTo(methodOn(EspecieControllerV2.class).getAllEspecies()).withSelfRel()
    ));
  }
    

  @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Especie>> getEspecieById(@PathVariable Long id) {
    Especie especie = especieService.findById(id);
    if (especie == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(especie));
  }

  @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Especie>> createEspecie(@RequestBody Especie especie) {
    Especie newEspecie = especieService.save(especie);
    return ResponseEntity
        .created(linkTo(methodOn(EspecieControllerV2.class).getEspecieById(newEspecie.getId())).toUri())
        .body(assembler.toModel(newEspecie));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<Especie>> updateEspecie(@PathVariable Long id, @RequestBody Especie especie) {
    Especie actualizado = especieService.actualizar(id, especie);
    return ResponseEntity.ok(assembler.toModel(actualizado));
  }


  @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<Void> deleteEspecie(@PathVariable Long id) {
    Especie especie = especieService.findById(id);
    if (especie == null) {
      return ResponseEntity.notFound().build();
    }
    especieService.delete(id);
    return ResponseEntity.noContent().build();
  }


  @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Especie>> patchEspecie(@PathVariable Long id, @RequestBody Especie especie) {
    Especie updatedEspecie = especieService.patchEspecie(id, especie);
    if (updatedEspecie == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(updatedEspecie));
  }

}
