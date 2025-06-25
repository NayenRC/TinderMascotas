package com.proyecto_byp.cl.proyecto_byp.repository;

import com.proyecto_byp.cl.proyecto_byp.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long> {
}