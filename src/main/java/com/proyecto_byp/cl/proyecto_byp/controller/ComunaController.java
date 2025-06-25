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

import com.proyecto_byp.cl.proyecto_byp.model.Comuna;
import com.proyecto_byp.cl.proyecto_byp.service.ComunaService;


@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> getAll() {
        return ResponseEntity.ok(comunaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getById(@PathVariable Long id) {
        Comuna comuna = comunaService.findById(id);
        if(comuna == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comuna);
    }

    @PostMapping
    public ResponseEntity<Comuna> create(@RequestBody Comuna comuna) {
        return ResponseEntity.ok(comunaService.save(comuna));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> update(@PathVariable Long id, @RequestBody Comuna comuna) {
        try{
            comunaService.save(comuna);
            return ResponseEntity.ok(comuna);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> PatchMapping(@PathVariable Long id, @RequestBody Comuna comunaParcial){
        try {
            Comuna comunaActualizado = comunaService.patchComuna(id, comunaParcial);
            return ResponseEntity.ok(comunaActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            comunaService.delete(id);
                return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
