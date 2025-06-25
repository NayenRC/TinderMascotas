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

import com.proyecto_byp.cl.proyecto_byp.model.Raza;
import com.proyecto_byp.cl.proyecto_byp.service.RazaService;

@RestController
@RequestMapping("/api/v1/razas")
public class RazaController {
    @Autowired
    private RazaService razaService;

    @GetMapping
    public ResponseEntity<List<Raza>> getAll() {
        return ResponseEntity.ok(razaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Raza> getById(@PathVariable Long id) {
        Raza raza = razaService.findById(id);
        if(raza == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(raza);
    }
    @PostMapping
    public ResponseEntity<Raza> create(@RequestBody Raza raza) {
        return ResponseEntity.ok(razaService.save(raza));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raza> update(@PathVariable Long id, @RequestBody  Raza raza) {
        try{
            razaService.save(raza);
            return ResponseEntity.ok(raza);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Raza> PatchMapping(@PathVariable Long id, @RequestBody  Raza razaParcial){
        try {
            Raza razaActualizado = razaService.patchRaza(id, razaParcial);
            return ResponseEntity.ok(razaActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            razaService.delete(id);
                return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}