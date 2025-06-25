package com.proyecto_byp.cl.proyecto_byp.service;

import com.proyecto_byp.cl.proyecto_byp.model.Animal;

import com.proyecto_byp.cl.proyecto_byp.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Animal findById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public void delete(Long id) {
        animalRepository.deleteById(id);
    }

    public Animal actualizar(long id, Animal animal) {
    Animal a = animalRepository.findById(id).orElse(null);
    if (a != null) {
        a.setNombre(animal.getNombre());
        a.setSexo(animal.getSexo());
        a.setEdad(animal.getEdad());
        a.setDescripcion(animal.getDescripcion());
        a.setFechaRegistro(animal.getFechaRegistro());
        a.setUsuario(animal.getUsuario());
        a.setRaza(animal.getRaza());
        return animalRepository.save(a);
    } else {
        return null;
    }
}
    public Animal patchAnimal (Long id, Animal animalParcial){
        Optional<Animal> animalOptional = animalRepository.findById(id);
        if(animalOptional.isPresent()){
            Animal animalToUpdate = animalOptional.get();

            if(animalParcial.getNombre()!=null){
                animalToUpdate.setNombre(animalParcial.getNombre());
            }
            if(animalParcial.getSexo()!=null){
                animalToUpdate.setSexo(animalParcial.getSexo());
            }
            if(animalParcial.getEdad()!=null){
                animalToUpdate.setEdad(animalParcial.getEdad());
            }
            if(animalParcial.getDescripcion()!=null){
                animalToUpdate.setDescripcion(animalParcial.getDescripcion());
            }
            if(animalParcial.getFechaRegistro()!=null){
                animalToUpdate.setFechaRegistro(animalParcial.getFechaRegistro());
            }
            if(animalParcial.getUsuario()!=null){
                animalToUpdate.setUsuario(animalParcial.getUsuario());
            }
            if(animalParcial.getRaza()!=null){
                animalToUpdate.setRaza(animalParcial.getRaza());
            }
            return animalRepository.save(animalToUpdate);
            }else{
                return null;
            }
    }

}