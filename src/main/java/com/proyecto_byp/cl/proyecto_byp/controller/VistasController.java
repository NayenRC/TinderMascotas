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

import com.proyecto_byp.cl.proyecto_byp.model.Vistas;
import com.proyecto_byp.cl.proyecto_byp.service.VistasService;

@RestController
@RequestMapping("/api/v1/vistas")
public class VistasController {
    @Autowired
    private VistasService vistasService;

    @GetMapping
    public ResponseEntity<List<Vistas>> getAll() {
        return ResponseEntity.ok(vistasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vistas> getById(@PathVariable Long id) {
        Vistas vistas = vistasService.findById(id);
        if(vistas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vistas);
    }

    @PostMapping
    public ResponseEntity<Vistas> create(@RequestBody Vistas vistas) {
        return ResponseEntity.ok(vistasService.save(vistas));
    }

@PutMapping("/{id}")
    public ResponseEntity<Vistas> update(@PathVariable Long id, @RequestBody  Vistas vistas) {
        try{
            vistasService.save(vistas);
            return ResponseEntity.ok(vistas);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vistas> PatchMapping(@PathVariable Long id, @RequestBody  Vistas vistasParcial){
        try {
            Vistas vistasActualizado = vistasService.patchVista(id, vistasParcial);
            return ResponseEntity.ok(vistasActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            vistasService.delete(id);
                return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}