package com.proyecto_byp.cl.proyecto_byp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.service.EspecieService;


@RestController
@RequestMapping("/api/v1/especies")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping
    public ResponseEntity<List<Especie>> getAll() {
        return ResponseEntity.ok(especieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> getById(@PathVariable Long id) {
        Especie especie = especieService.findById(id);
        if(especie == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(especie);
    }

    @PostMapping
    public ResponseEntity<Especie> create(@RequestBody Especie especie) {
        return ResponseEntity.ok(especieService.save(especie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especie> update(@PathVariable Long id, @RequestBody Especie especie) {
        try{
            especieService.save(especie);
            return ResponseEntity.ok(especie);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Especie> PatchMapping(@PathVariable Long id, @RequestBody Especie especieParcial){
        try {
            Especie especieActualizado = especieService.patchEspecie(id, especieParcial);
            return ResponseEntity.ok(especieActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            especieService.delete(id);
                return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

