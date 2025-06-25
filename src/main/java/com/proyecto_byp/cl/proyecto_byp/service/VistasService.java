package com.proyecto_byp.cl.proyecto_byp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_byp.cl.proyecto_byp.model.Vistas;
import com.proyecto_byp.cl.proyecto_byp.repository.VistasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VistasService {

    @Autowired
    private VistasRepository vistasRepository;

    public List<Vistas> findAll() {
        return vistasRepository.findAll();
    }

    public Vistas findById(Long id) {
        return vistasRepository.findById(id).orElse(null);
    }

    public Vistas save(Vistas vista) {
        return vistasRepository.save(vista);
    }

    public void delete(Long id) {
        vistasRepository.deleteById(id);
    }

    public Vistas actualizar(long id, Vistas vistas) {
    Vistas v = vistasRepository.findById(id).orElse(null);
    if (v != null) {
        v.setMatchStatus(vistas.getMatchStatus());
        v.setLike1(vistas.getLike1());
        v.setLike2(vistas.getLike2());
        v.setAnimal(vistas.getAnimal());
        return vistasRepository.save(v);
    } else {
        return null;
    }
}

    public Vistas patchVista(long id, Vistas vistasParcial) {
    Optional<Vistas> optional = vistasRepository.findById(id);
    if (optional.isPresent()) {
        Vistas vistaToUpdate = optional.get();

        if (vistasParcial.getMatchStatus() != null) {
            vistaToUpdate.setMatchStatus(vistasParcial.getMatchStatus());
        }
        if (vistasParcial.getLike1() != null) {
            vistaToUpdate.setLike1(vistasParcial.getLike1());
        }
        if (vistasParcial.getLike2() != null) {
            vistaToUpdate.setLike2(vistasParcial.getLike2());
        }
        if (vistasParcial.getAnimal() != null) {
            vistaToUpdate.setAnimal(vistasParcial.getAnimal());
        }
        return vistasRepository.save(vistaToUpdate);
    }
    return null;
}
    
}