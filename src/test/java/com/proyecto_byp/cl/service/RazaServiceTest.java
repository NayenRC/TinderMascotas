package com.proyecto_byp.cl.service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import com.proyecto_byp.cl.proyecto_byp.model.Raza;
import com.proyecto_byp.cl.proyecto_byp.repository.RazaRepository;
import com.proyecto_byp.cl.proyecto_byp.service.RazaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



public class RazaServiceTest {

@Mock
private RazaRepository razaRepository;

@InjectMocks
private RazaService razaService;



@BeforeEach
void setUp() {
MockitoAnnotations.openMocks(this);
}



@Test
void testFindAll() {
Raza r1 = new Raza();
Raza r2 = new Raza();
Mockito.when(razaRepository.findAll())
    .thenReturn(Arrays.asList(r1, r2));



List<Raza> lista = razaService.findAll();
Assertions.assertEquals(2, lista.size());
Mockito.verify(razaRepository, Mockito.times(1))
    .findAll();
}



@Test

void testFindByIdFound() {
Raza r = new Raza();
r.setId(1L);



Mockito.when(razaRepository.findById(1L))
    .thenReturn(Optional.of(r));



Raza resultado = razaService.findById(1L);
Assertions.assertNotNull(resultado);
Assertions.assertEquals(1L, resultado.getId());
}



@Test
void testSave() {
Raza r = new Raza();
r.setNombre("Labrador");
Mockito.when(razaRepository.save(r))
    .thenReturn(r);



Raza creado = razaService.save(r);
Assertions.assertEquals("Labrador", creado.getNombre());
Mockito.verify(razaRepository).save(r);
}



@Test
void testDelete() {
razaService.delete(1L);
Mockito.verify(razaRepository, Mockito.times(1))
    .deleteById(1L);
}

}

