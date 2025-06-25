package com.proyecto_byp.cl.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.proyecto_byp.cl.proyecto_byp.model.Usuario;
import com.proyecto_byp.cl.proyecto_byp.repository.UsuarioRepository;
import com.proyecto_byp.cl.proyecto_byp.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;



public class UsuarioServiceTest {


@Mock
private UsuarioRepository usuarioRepository;



@InjectMocks
private UsuarioService usuarioService;



@BeforeEach
void setUp() {
MockitoAnnotations.openMocks(this);

}



@Test
void testFindAll() {
Usuario u1 = new Usuario();
Usuario u2 = new Usuario();
Mockito.when(usuarioRepository.findAll())
    .thenReturn(Arrays.asList(u1, u2));

List<Usuario> lista = usuarioService.findAll();
Assertions.assertEquals(2, lista.size());
Mockito.verify(usuarioRepository, Mockito.times(1)).findAll();

}



@Test

void testFindByIdFound() {
Usuario u = new Usuario();
u.setId(1L);
Mockito.when(usuarioRepository.findById(1L))
    .thenReturn(Optional.of(u));



Usuario resultado = usuarioService.findById(1L);
Assertions.assertNotNull(resultado);
Assertions.assertEquals(1L, resultado.getId());

}


@Test
void testSave() {
Usuario u = new Usuario();
u.setNombre("Ana");
Mockito.when(usuarioRepository.save(u))
    .thenReturn(u);

Usuario creado = usuarioService.save(u);
Assertions.assertEquals("Ana", creado.getNombre());
Mockito.verify(usuarioRepository).save(u);
}



@Test

void testDelete() {

usuarioService.delete(1L);
Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(1L);

}

}

