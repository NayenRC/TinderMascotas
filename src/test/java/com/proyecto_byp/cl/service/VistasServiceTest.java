package com.proyecto_byp.cl.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import com.proyecto_byp.cl.proyecto_byp.model.Vistas;
import com.proyecto_byp.cl.proyecto_byp.repository.VistasRepository;
import com.proyecto_byp.cl.proyecto_byp.service.VistasService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class VistasServiceTest {


@Mock

private VistasRepository vistasRepository;

@InjectMocks
private VistasService vistasService;


@BeforeEach
void setUp() {
MockitoAnnotations.openMocks(this);

}



@Test
void testFindAll() {
Vistas v1 = new Vistas();
Vistas v2 = new Vistas();
Mockito.when(vistasRepository.findAll())
    .thenReturn(Arrays.asList(v1, v2));



List<Vistas> lista = vistasService.findAll();

Assertions.assertEquals(2, lista.size());

Mockito.verify(vistasRepository, Mockito.times(1))
    .findAll();
}



@Test
void testFindByIdFound() {
Vistas v = new Vistas();
v.setId(1L);



Mockito.when(vistasRepository.findById(1L))
    .thenReturn(Optional.of(v));
Vistas resultado = vistasService.findById(1L);
Assertions.assertNotNull(resultado);
Assertions.assertEquals(1L, resultado.getId());
}



@Test
void testSave() {
Vistas v = new Vistas();
// aquí podrías setear campos si los tienes en Vistas
Mockito.when(vistasRepository.save(v))
    .thenReturn(v);


Vistas creado = vistasService.save(v);
// Ajusta esta aserción al campo que prefieras

Assertions.assertNotNull(creado);
Mockito.verify(vistasRepository).save(v);
}



@Test
void testDelete() {
vistasService.delete(1L);
Mockito.verify(vistasRepository, Mockito.times(1))
    .deleteById(1L);
}

}