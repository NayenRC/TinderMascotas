package com.proyecto_byp.cl.proyecto_byp.service;

import com.proyecto_byp.cl.proyecto_byp.model.Comuna;
import com.proyecto_byp.cl.proyecto_byp.repository.ComunaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Comuna findById(long id ) {
        return comunaRepository.findById(id).orElse(null);
    }

    public Comuna save(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public void delete(Long id) {
        comunaRepository.deleteById(id);
    }

    public Comuna actualizar(long id, Comuna comuna) {
    Comuna c = comunaRepository.findById(id).orElse(null);
    if (c != null) {
        c.setNombre(comuna.getNombre());
        c.setRegion(comuna.getRegion());
        return comunaRepository.save(c);
    } else {
        return null;
    }
}

    public Comuna patchComuna(long id, Comuna comunaParcial) {
    Optional<Comuna> optional = comunaRepository.findById(id);
    if (optional.isPresent()) {
        Comuna comunaToUpdate = optional.get();
        if (comunaParcial.getNombre() != null) {
            comunaToUpdate.setNombre(comunaParcial.getNombre());
        }
        if (comunaParcial.getRegion() != null) {
            comunaToUpdate.setRegion(comunaParcial.getRegion());
        }
        return comunaRepository.save(comunaToUpdate);
    }
    return null;
}
}