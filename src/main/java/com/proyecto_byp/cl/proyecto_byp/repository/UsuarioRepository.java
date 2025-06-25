package com.proyecto_byp.cl.proyecto_byp.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto_byp.cl.proyecto_byp.model.Usuario;
import com.proyecto_byp.cl.proyecto_byp.model.Vistas;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT v FROM Vistas v WHERE v.matchStatus = 'Y' AND v.animal.usuario.id = :usuarioId")
    List<Vistas> findMatchesByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COUNT(a) FROM Animal a WHERE a.usuario.id = :usuarioId")
    Long contarMascotasPorUsuario(@Param("usuarioId") Long usuarioId);
}


