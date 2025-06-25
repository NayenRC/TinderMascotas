package com.proyecto_byp.cl.proyecto_byp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyecto_byp.cl.proyecto_byp.model.Usuario;
import com.proyecto_byp.cl.proyecto_byp.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(Usuario usuario) {
        // Encriptar la clave antes de guardar
        String claveEncriptada = passwordEncoder.encode(usuario.getClave());
        usuario.setClave(claveEncriptada);

        return usuarioRepository.save(usuario);
    }
    
    
    public Long contarMascotas(Long usuarioId) {
        return usuarioRepository.contarMascotasPorUsuario(usuarioId);
    }


    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById (Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
    if (usuario.getClave() != null) {
        String claveEncriptada = passwordEncoder.encode(usuario.getClave());
        usuario.setClave(claveEncriptada);
    }
    return usuarioRepository.save(usuario);
}


    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizar(Long id, Usuario usuario) {
    Usuario us = usuarioRepository.findById(id).orElse(null);
    if (us != null) {
        us.setNombre(usuario.getNombre());
        us.setCorreo(usuario.getCorreo());
        if (usuario.getClave() != null) {
            String claveEncriptada = passwordEncoder.encode(usuario.getClave());
            us.setClave(claveEncriptada);
        }
        us.setTelefono(usuario.getTelefono());
        us.setDireccion(usuario.getDireccion());
        us.setTipo(usuario.getTipo());
        us.setFechaRegistro(usuario.getFechaRegistro());
        us.setComuna(usuario.getComuna());
        return usuarioRepository.save(us);
    } else {
        return null;
    }
}

    public Usuario patchUsuario(Long id, Usuario usuarioParcial) {
    Optional<Usuario> usuarionOptional = usuarioRepository.findById(id);
    if (usuarionOptional.isPresent()) {
        Usuario usuarioToUpdate = usuarionOptional.get();

        if (usuarioParcial.getNombre() != null) {
            usuarioToUpdate.setNombre(usuarioParcial.getNombre());
        }
        if (usuarioParcial.getCorreo() != null) {
            usuarioToUpdate.setCorreo(usuarioParcial.getCorreo());
        }
        if (usuarioParcial.getClave() != null) {
            String claveEncriptada = passwordEncoder.encode(usuarioParcial.getClave());
            usuarioToUpdate.setClave(claveEncriptada);
        }
        if (usuarioParcial.getTelefono() != null) {
            usuarioToUpdate.setTelefono(usuarioParcial.getTelefono());
        }
        if (usuarioParcial.getDireccion() != null) {
            usuarioToUpdate.setDireccion(usuarioParcial.getDireccion());
        }
        if (usuarioParcial.getTipo() != null) {
            usuarioToUpdate.setTipo(usuarioParcial.getTipo());
        }
        if (usuarioParcial.getFechaRegistro() != null) {
            usuarioToUpdate.setFechaRegistro(usuarioParcial.getFechaRegistro());
        }
        if (usuarioParcial.getComuna() != null) {
            usuarioToUpdate.setComuna(usuarioParcial.getComuna());
        }
        return usuarioRepository.save(usuarioToUpdate);
    } else {
        return null;
    }
}
}