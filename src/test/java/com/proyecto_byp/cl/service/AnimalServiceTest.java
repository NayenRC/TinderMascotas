package com.proyecto_byp.cl.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import com.proyecto_byp.cl.proyecto_byp.model.Animal;
import com.proyecto_byp.cl.proyecto_byp.repository.AnimalRepository;
import com.proyecto_byp.cl.proyecto_byp.service.AnimalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;



public class AnimalServiceTest {

@Mock
private AnimalRepository animalRepository;



@InjectMocks
private AnimalService animalService;



@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}



@Test
void testFindAll() {
    Animal a1 = new Animal();
    Animal a2 = new Animal();
    Mockito.when(animalRepository.findAll())
        .thenReturn(Arrays.asList(a1, a2));


    List<Animal> lista = animalService.findAll();
    Assertions.assertEquals(2, lista.size());
    Mockito.verify(animalRepository, Mockito.times(1)).findAll();
  }



  @Test
  void testFindByIdFound() {
    Animal a = new Animal();
    a.setId(1L);

    Mockito.when(animalRepository.findById(1L))
        .thenReturn(Optional.of(a));

    Animal resultado = animalService.findById(1L);
    Assertions.assertNotNull(resultado);
    Assertions.assertEquals(1L, resultado.getId());
  }

  @Test
  void testSave() {
    Animal a = new Animal();
    a.setNombre("Firulais");

    Mockito.when(animalRepository.save(a))
        .thenReturn(a);

    Animal creado = animalService.save(a);
    Assertions.assertEquals("Firulais", creado.getNombre());
    Mockito.verify(animalRepository).save(a);
  }

  @Test
  void testDelete() {
    animalService.delete(1L);
    Mockito.verify(animalRepository, Mockito.times(1)).deleteById(1L);
  }

}