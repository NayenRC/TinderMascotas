package com.proyecto_byp.cl.proyecto_byp.service;

import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.repository.EspecieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> findAll() {
        return especieRepository.findAll();
    }

    public Especie findById(Long id) {
        return especieRepository.findById(id).orElse(null);
    }

    public Especie save(Especie especie) {
        return especieRepository.save(especie);
    }

    public void delete(Long id) {
        especieRepository.deleteById(id);
    }

    public Especie actualizar(long id, Especie especie) {
    Especie e = especieRepository.findById(id).orElse(null);
    if (e != null) {
        e.setNombre(especie.getNombre());
        e.setTipo(especie.getTipo());
        return especieRepository.save(e);
    } else {
        return null;
    }
}
    public Especie patchEspecie(long id, Especie especieParcial) {
    Optional<Especie> optional = especieRepository.findById(id);
    if (optional.isPresent()) {
        Especie especieToUpdate = optional.get();

        if (especieParcial.getNombre() != null) {
            especieToUpdate.setNombre(especieParcial.getNombre());
        }
        if (especieParcial.getTipo() != null) {
            especieToUpdate.setTipo(especieParcial.getTipo());
        }
        return especieRepository.save(especieToUpdate);
    }
    return null;
}
}