package com.proyecto_byp.cl.proyecto_byp.controller.v2;

import com.proyecto_byp.cl.proyecto_byp.service.RegionService;

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



import com.proyecto_byp.cl.proyecto_byp.assembler.RegionAssembler;
import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.model.Region;

@RestController
@RequestMapping("/api/v2/regiones")
public class RegionControllerV2 {
  @Autowired

  private RegionService regionService;
  @Autowired
  private RegionAssembler assembler;

  @GetMapping (produces=MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<CollectionModel<EntityModel<Region>>> getAllRegion() {
      List<EntityModel<Region>> region = regionService.findAll().stream().map(assembler::toModel)
          .collect(Collectors.toList());
      if(region.isEmpty()){
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(CollectionModel.of(
          region,
          linkTo(methodOn(RegionControllerV2.class).getAllRegion()).withSelfRel()
      ));
  }



  @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Region>> getRegionById(@PathVariable Long id) {
    Region region = regionService.findById(id);
    if (region == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(region));
  }



  @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Region>> createRegion(@RequestBody Region region) {
    Region newRegion = regionService.save(region);
    return ResponseEntity
        .created(linkTo(methodOn(RegionControllerV2.class).getRegionById(newRegion.getId())).toUri())
        .body(assembler.toModel(newRegion));
  }



  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<Region>> updateRegion(@PathVariable Long id, @RequestBody Region region) {
    Region actualizado = regionService.actualizar(id, region);
    return ResponseEntity.ok(assembler.toModel(actualizado));
  }



  @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
    Region region = regionService.findById(id);
    if (region == null) {
      return ResponseEntity.notFound().build();
    }
    regionService.delete(id);
    return ResponseEntity.noContent().build();
  }


  @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<EntityModel<Region>> patchRegion(@PathVariable Long id,@RequestBody Region region) {
    Region updatedRegion = regionService.patchRegion(id, region);
    if (updatedRegion == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(assembler.toModel(updatedRegion));
}


}

