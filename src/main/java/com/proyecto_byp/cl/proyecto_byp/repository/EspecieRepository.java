package com.proyecto_byp.cl.proyecto_byp.repository;

import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
