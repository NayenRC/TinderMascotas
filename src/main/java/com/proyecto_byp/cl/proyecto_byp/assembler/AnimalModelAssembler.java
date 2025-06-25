package com.proyecto_byp.cl.proyecto_byp.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.proyecto_byp.cl.proyecto_byp.controller.v2.AnimalControllerV2;
import com.proyecto_byp.cl.proyecto_byp.model.Animal;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AnimalModelAssembler implements RepresentationModelAssembler<Animal, EntityModel<Animal>> {

    @Override
    public EntityModel<Animal> toModel(Animal animal) {
        return EntityModel.of(animal,
                linkTo(methodOn(AnimalControllerV2.class).getAnimalById(animal.getId())).withSelfRel(),
                linkTo(methodOn(AnimalControllerV2.class).getAllAnimales()).withRel("animales"),
                linkTo(methodOn(AnimalControllerV2.class).updateAnimal(animal.getId(), animal)).withRel("actualizar"),
                linkTo(methodOn(AnimalControllerV2.class).deleteAnimal(animal.getId())).withRel("eliminar"),
                linkTo(methodOn(AnimalControllerV2.class).patchAnimal(animal.getId(), animal)).withRel("actualizar-parcial")
        );
    }
}





//LO DEJO ASI POR Q HABIAN 2 Y NO SE CUAL DE LOS 2 ERA 
//import com.proyecto_byp.cl.proyecto_byp.controller.v2.AnimalControllerV2;
//import com.proyecto_byp.cl.proyecto_byp.model.Animal;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.RepresentationModelAssembler;
//import org.springframework.stereotype.Component;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@Component
//public class AnimalAssembler implements RepresentationModelAssembler<Animal, EntityModel<Animal>> {

//   @Override
//    public EntityModel<Animal> toModel(Animal animal) {
//        return EntityModel.of(
//            animal,
//            // enlace a sí mismo
//            linkTo(methodOn(AnimalControllerV2.class)
//                .getAnimalById(animal.getId())
//            ).withSelfRel(),
//            // enlace a la colección
//            linkTo(methodOn(AnimalControllerV2.class)
//                .getAllAnimales()
//            ).withRel("animales"),
//            // enlace a actualizar (PUT)
//            linkTo(methodOn(AnimalControllerV2.class)
//                .updateAnimal(animal.getId(), animal)
//            ).withRel("actualizar"),
//            // enlace a eliminar (DELETE)
//            linkTo(methodOn(AnimalControllerV2.class)
//              .deleteAnimal(animal.getId())
//           ).withRel("eliminar"),
//          // enlace a parcheo (PATCH)
//            linkTo(methodOn(AnimalControllerV2.class)
//             .patchAnimal(animal.getId(), animal)
//           ).withRel("actualizar-parcial")
//       );
//    }

//}


