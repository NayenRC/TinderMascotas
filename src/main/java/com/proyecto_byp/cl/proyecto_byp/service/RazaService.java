package com.proyecto_byp.cl.proyecto_byp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_byp.cl.proyecto_byp.model.Raza;
import com.proyecto_byp.cl.proyecto_byp.repository.RazaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RazaService {

    @Autowired
    private RazaRepository razaRepository;

    public List<Raza> findAll() {
        return razaRepository.findAll();
    }

    public Raza findById(Long id) {
        return razaRepository.findById(id).orElse(null);
    }

    public Raza save(Raza raza) {
        return razaRepository.save(raza);
    }

    public void delete(Long id) {
        razaRepository.deleteById(id);
    }

    public Raza actualizar(long id, Raza raza) {
        Raza r = razaRepository.findById(id).orElse(null);
        if (r != null) {
            r.setNombre(raza.getNombre());
            r.setEspecie(raza.getEspecie());
            return save(r);
        } else {
            return null;
        }
    }

    public Raza patchRaza(long id, Raza razaParcial) {
        Raza existingRaza = findById(id);
        if (existingRaza != null){
        if (razaParcial.getNombre() != null) {
            existingRaza.setNombre(razaParcial.getNombre());
        }
        if (razaParcial.getEspecie() != null) {
            existingRaza.setEspecie(razaParcial.getEspecie());
        }
        return save(existingRaza);
    }
    return null;
}
}