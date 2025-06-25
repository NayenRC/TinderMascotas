package com.proyecto_byp.cl.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.proyecto_byp.cl.proyecto_byp.model.Comuna;
import com.proyecto_byp.cl.proyecto_byp.repository.ComunaRepository;
import com.proyecto_byp.cl.proyecto_byp.service.ComunaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;

public class ComunaServiceTest {
@Mock
private ComunaRepository comunaRepository;

@InjectMocks
private ComunaService comunaService;



@BeforeEach
void setUp() {
MockitoAnnotations.openMocks(this);
}


@Test
void testFindAll() {
Comuna c1 = new Comuna();
Comuna c2 = new Comuna();

Mockito.when(comunaRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

List<Comuna> lista = comunaService.findAll();
Assertions.assertEquals(2, lista.size());
Mockito.verify(comunaRepository, Mockito.times(1))
    .findAll();
}



@Test
void testFindByIdFound() {
Comuna c = new Comuna();
c.setId(1L);            // ← aquí Integer en lugar de 1L

Mockito.when(comunaRepository.findById(1L))
    .thenReturn(Optional.of(c)); // ← findById(1), no 1L



Comuna resultado = comunaService.findById(1);
Assertions.assertNotNull(resultado);
Assertions.assertEquals(1, resultado.getId()); // ← comparar int/Integer
}



@Test
void testSave() {
Comuna c = new Comuna();
c.setNombre("Providencia");
Mockito.when(comunaRepository.save(c))
    .thenReturn(c);


Comuna creado = comunaService.save(c);
Assertions.assertEquals("Providencia", creado.getNombre());
Mockito.verify(comunaRepository).save(c);
}

@Test
void testDelete() {
comunaService.delete(1L);
Mockito.verify(comunaRepository, Mockito.times(1))
    .deleteById(1L);
}

}