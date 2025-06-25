package com.proyecto_byp.cl.proyecto_byp.controller.v2;

import com.proyecto_byp.cl.proyecto_byp.service.VistasService;

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



import com.proyecto_byp.cl.proyecto_byp.assembler.VistasAssembler;
import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.model.Vistas;



@RestController
@RequestMapping("/api/v2/vistas")
public class VistasControllerV2 {

  @Autowired
  private VistasService vistasService;

  @Autowired
  private VistasAssembler assembler;

  @GetMapping (produces=MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<CollectionModel<EntityModel<Vistas>>> getAllVistas() {
      List<EntityModel<Vistas>> vistas = vistasService.findAll().stream().map(assembler::toModel)
            .collect(Collectors.toList());
      if(vistas.isEmpty()){
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(CollectionModel.of(
            vistas,
            linkTo(methodOn(VistasControllerV2.class).getAllVistas()).withSelfRel()
      ));
  }


  @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Vistas>> getVistaById(@PathVariable Long id) {
      Vistas vista = vistasService.findById(id);
      if (vista == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(assembler.toModel(vista));
  }


  @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Vistas>> createVista(@RequestBody Vistas vista) {
      Vistas newVista = vistasService.save(vista);
      return ResponseEntity
          .created(linkTo(methodOn(VistasControllerV2.class).getVistaById(newVista.getId())).toUri())
          .body(assembler.toModel(newVista));
  }



  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<Vistas>> updateVista(@PathVariable Long id, @RequestBody Vistas vista) {
      Vistas actualizado = vistasService.actualizar(id, vista);
      return ResponseEntity.ok(assembler.toModel(actualizado));
  }



  @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<Void> deleteVista(@PathVariable Long id) {
      Vistas vista = vistasService.findById(id);
      if (vista == null) {
          return ResponseEntity.notFound().build();
      }

      vistasService.delete(id);
      return ResponseEntity.noContent().build();
  }



  @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Vistas>> patchVista(@PathVariable Long id, @RequestBody Vistas vista) {
      Vistas updatedVista = vistasService.patchVista(id, vista);
      if (updatedVista == null) {
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(assembler.toModel(updatedVista));
  }
}