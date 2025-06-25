package com.proyecto_byp.cl.service;

import java.util.Arrays;

import java.util.List;

import java.util.Optional;



import com.proyecto_byp.cl.proyecto_byp.model.Region;

import com.proyecto_byp.cl.proyecto_byp.repository.RegionRepository;
import com.proyecto_byp.cl.proyecto_byp.service.RegionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class RegionServiceTest {



@Mock
private RegionRepository regionRepository;

@InjectMocks
private RegionService regionService;


@BeforeEach
void setUp() {
MockitoAnnotations.openMocks(this);
}



@Test
void testFindAll() {
Region r1 = new Region();
Region r2 = new Region();
Mockito.when(regionRepository.findAll())
    .thenReturn(Arrays.asList(r1, r2));



List<Region> lista = regionService.findAll();
Assertions.assertEquals(2, lista.size());
Mockito.verify(regionRepository, Mockito.times(1))
    .findAll();
}



@Test
void testFindByIdFound() {
Region r = new Region();
r.setId(1L);



Mockito.when(regionRepository.findById(1L))
    .thenReturn(Optional.of(r));
Region resultado = regionService.findById(1L);
Assertions.assertNotNull(resultado);
Assertions.assertEquals(1L, resultado.getId());
}



@Test
void testSave() {
Region r = new Region();
r.setNombre("Metropolitana");

Mockito.when(regionRepository.save(r))
    .thenReturn(r);

Region creado = regionService.save(r);
Assertions.assertEquals("Metropolitana", creado.getNombre());
Mockito.verify(regionRepository).save(r);
}



@Test
void testDelete() {
regionService.delete(1L);
Mockito.verify(regionRepository, Mockito.times(1))
    .deleteById(1L);

}

}

