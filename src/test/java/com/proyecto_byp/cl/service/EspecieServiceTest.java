package com.proyecto_byp.cl.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import com.proyecto_byp.cl.proyecto_byp.model.Especie;
import com.proyecto_byp.cl.proyecto_byp.repository.EspecieRepository;
import com.proyecto_byp.cl.proyecto_byp.service.EspecieService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class EspecieServiceTest {

@Mock
private EspecieRepository especieRepository;

@InjectMocks
private EspecieService especieService;



@BeforeEach
void setUp() {
MockitoAnnotations.openMocks(this);
}



@Test
void testFindAll() {
Especie e1 = new Especie();
Especie e2 = new Especie();
Mockito.when(especieRepository.findAll())
    .thenReturn(Arrays.asList(e1, e2));



List<Especie> lista = especieService.findAll();
Assertions.assertEquals(2, lista.size());
Mockito.verify(especieRepository, Mockito.times(1))
    .findAll();
}



@Test
void testFindByIdFound() {
Especie e = new Especie();
e.setId(1L);



Mockito.when(especieRepository.findById(1L))
    .thenReturn(Optional.of(e));
Especie resultado = especieService.findById(1L);
Assertions.assertNotNull(resultado);
Assertions.assertEquals(1L, resultado.getId());
}



@Test
void testSave() {
Especie e = new Especie();
e.setNombre("Felino");
Mockito.when(especieRepository.save(e))
    .thenReturn(e);
Especie creado = especieService.save(e);
Assertions.assertEquals("Felino", creado.getNombre());
Mockito.verify(especieRepository).save(e);
}


@Test
void testDelete() {
especieService.delete(1L);
Mockito.verify(especieRepository, Mockito.times(1))
    .deleteById(1L);
}

}

