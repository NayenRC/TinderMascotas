package com.proyecto_byp.cl.proyecto_byp.controller.v2;
import com.proyecto_byp.cl.proyecto_byp.assembler.AnimalModelAssembler;
import com.proyecto_byp.cl.proyecto_byp.model.Animal;
import com.proyecto_byp.cl.proyecto_byp.service.AnimalService;


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


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



@RestController

@RequestMapping("/api/v2/animales")

public class AnimalControllerV2 {
    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalModelAssembler assembler;

    //definir endpoints 
@GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<CollectionModel<EntityModel<Animal>>> getAllAnimales() {
    //obtiene los usuarios a partir de la lista creada en el service 
    List<Animal> lista = animalService.findAll(); //metododo findAll
    CollectionModel<EntityModel<Animal>> coleccion = assembler.toCollectionModel(lista);
    if (coleccion.getContent().isEmpty())
        return ResponseEntity.noContent().build();
    return ResponseEntity.ok(coleccion);
    }



@GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Animal>> getAnimalById(@PathVariable Long id) {
    Animal animal = animalService.findById(id);
    if (animal == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(animal));
    }
//Crear un usuario y guardarlo por su id
@PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Animal>> createAnimal(@RequestBody Animal animal) {
    Animal guardado = animalService.save(animal);
    return ResponseEntity
        .created(linkTo(
        methodOn(AnimalControllerV2.class).getAnimalById(guardado.getId())
        ).toUri())
        .body(assembler.toModel(guardado));
    }

//updateAnimal
@PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Animal>> updateAnimal(@PathVariable Long id,@RequestBody Animal animal) {
    animal.setId(id);
    Animal actualizado = animalService.save(animal);
    return ResponseEntity.ok(assembler.toModel(actualizado));
    }

//patch actualizar de manera parcial
@PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Animal>> patchAnimal(@PathVariable Long id, @RequestBody Animal animalParcial) {
    Animal actualizado = animalService.patchAnimal(id, animalParcial);
    if (actualizado == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(actualizado));
}

//eliminar, buscar por id , s esta nulo
@DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
    Animal animal = animalService.findById(id);
    if (animal == null) {
        return ResponseEntity.notFound().build();
    }
    animalService.delete(id);
    return ResponseEntity.noContent().build();

}

}

