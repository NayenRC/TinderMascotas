package com.proyecto_byp.cl.proyecto_byp.controller.v2;

import com.proyecto_byp.cl.proyecto_byp.service.RazaService;
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

import com.proyecto_byp.cl.proyecto_byp.assembler.RazaAssembler;
import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.model.Raza;



@RestController
@RequestMapping("/api/v2/razas")
public class RazaControllerV2 {
  @Autowired

  private RazaService razaService;
  @Autowired

  private RazaAssembler assembler;



  @GetMapping (produces=MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<CollectionModel<EntityModel<Raza>>> getAllRaza() {
      List<EntityModel<Raza>> raza = razaService.findAll().stream().map(assembler::toModel)
              .collect(Collectors.toList());
      if(raza.isEmpty()){
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(CollectionModel.of(
              raza,
              linkTo(methodOn(EspecieControllerV2.class).getAllEspecies()).withSelfRel()
      ));
  }



  @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)

  public ResponseEntity<EntityModel<Raza>> getRazaById(@PathVariable Long id) {

    Raza raza = razaService.findById(id);

    if (raza == null) {

      return ResponseEntity.notFound().build();

    }

    return ResponseEntity.ok(assembler.toModel(raza));

  }



  @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)

  public ResponseEntity<EntityModel<Raza>> createRaza(@RequestBody Raza raza) {

    Raza newRaza = razaService.save(raza);
    return ResponseEntity
        .created(linkTo(methodOn(RazaControllerV2.class).getRazaById(newRaza.getId())).toUri())
        .body(assembler.toModel(newRaza));
  }



  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<Raza>> updateRaza(@PathVariable Long id, @RequestBody Raza raza) {
    Raza actualizado = razaService.actualizar(id, raza);
    return ResponseEntity.ok(assembler.toModel(actualizado));
  }



  @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<Void> deleteRaza(@PathVariable Long id) {
    Raza raza = razaService.findById(id);
    if (raza == null) {
      return ResponseEntity.notFound().build();
    }
    razaService.delete(id);
    return ResponseEntity.noContent().build();
  }




@PatchMapping("/{id}")
  public ResponseEntity<EntityModel<Raza>> patchRaza(
    @PathVariable Long id,
    @RequestBody Raza razaParcial) {
    Raza razaActualizada = razaService.patchRaza(id, razaParcial);
    if (razaActualizada == null) {
        return ResponseEntity.notFound().build();
    }
    EntityModel<Raza> razaModel = assembler.toModel(razaActualizada);
    return ResponseEntity.ok(razaModel);
}


}