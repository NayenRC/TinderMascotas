package com.proyecto_byp.cl.proyecto_byp.repository;

import com.proyecto_byp.cl.proyecto_byp.model.Vistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VistasRepository extends JpaRepository<Vistas, Long> {
}
