package com.proyecto_byp.cl.proyecto_byp.controller.v2;

import com.proyecto_byp.cl.proyecto_byp.service.UsuarioService;

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



import com.proyecto_byp.cl.proyecto_byp.assembler.UsuarioAssembler;
import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.model.Usuario;
import com.proyecto_byp.cl.proyecto_byp.model.Vistas;



@RestController

@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private UsuarioAssembler assembler;

  @GetMapping (produces=MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<CollectionModel<EntityModel<Usuario>>> getAllUsuario() {
      List<EntityModel<Usuario>> usuario = usuarioService.findAll().stream().map(assembler::toModel)
            .collect(Collectors.toList());
      if(usuario.isEmpty()){
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(CollectionModel.of(
            usuario,
            linkTo(methodOn(UsuarioControllerV2.class).getAllUsuario()).withSelfRel()
      ));
  }



  @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Usuario>> getUsuarioById(@PathVariable Long id) {
    Usuario usuario = usuarioService.findById(id);
    if (usuario == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(usuario));
  }



  @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Usuario>> createUsuario(@RequestBody Usuario usuario) {
    Usuario newUsuario = usuarioService.save(usuario);
    return ResponseEntity
        .created(linkTo(methodOn(UsuarioControllerV2.class).getUsuarioById(newUsuario.getId())).toUri())
        .body(assembler.toModel(newUsuario));
  }



  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<Usuario>> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    Usuario actualizado = usuarioService.actualizar(id, usuario);
    return ResponseEntity.ok(assembler.toModel(actualizado));
  }



  @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
    Usuario usuario = usuarioService.findById(id);
    if (usuario == null) {
      return ResponseEntity.notFound().build();
    }
    usuarioService.delete(id);
    return ResponseEntity.noContent().build();
  }


  @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Usuario>> patchUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    Usuario updatedUsuario = usuarioService.patchUsuario(id, usuario);
    if (updatedUsuario == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(updatedUsuario));
  }
}