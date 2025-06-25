package com.proyecto_byp.cl.proyecto_byp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_byp.cl.proyecto_byp.model.Region;
import com.proyecto_byp.cl.proyecto_byp.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public void delete(Long id) {
        regionRepository.deleteById(id);
    }

    public Region actualizar(long id, Region region) {
    Region r = regionRepository.findById(id).orElse(null);
    if (r != null) {
        r.setNombre(region.getNombre());
        return regionRepository.save(r);
    } else {
        return null;
    }
}

    public Region patchRegion(Long id, Region regionParcial){
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()) {
            Region regionToUpdate = regionOptional.get();
            if (regionParcial.getNombre() != null) {
                regionToUpdate.setNombre(regionParcial.getNombre());
                }
                return regionRepository.save(regionToUpdate);
            }else{
                return null;
            }
    }
}