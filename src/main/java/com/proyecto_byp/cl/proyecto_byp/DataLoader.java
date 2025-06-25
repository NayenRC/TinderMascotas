package com.proyecto_byp.cl.proyecto_byp;
import com.proyecto_byp.cl.proyecto_byp.model.*;
import com.proyecto_byp.cl.proyecto_byp.repository.*;

import net.datafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private RegionRepository regionRepository;
    @Autowired private ComunaRepository comunaRepository;
    @Autowired private EspecieRepository especieRepository;
    @Autowired private RazaRepository razaRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private AnimalRepository animalRepository;
    @Autowired private VistasRepository vistasRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // 1. Crear regiones y comunas
        for (int i = 0; i < 3; i++) {
            Region region = new Region();
            region.setNombre("Región " + (i + 1));
            regionRepository.save(region);

            for (int j = 0; j < 3; j++) {
                Comuna comuna = new Comuna();
                comuna.setNombre(faker.address().cityName());
                comuna.setRegion(region);
                comunaRepository.save(comuna);
            }
        }

        List<Comuna> comunas = comunaRepository.findAll();

        // 2. Crear especies
        for (int i = 0; i < 3; i++) {
            Especie especie = new Especie();
            especie.setNombre(faker.animal().name());
            especieRepository.save(especie);
        }

        List<Especie> especies = especieRepository.findAll();

        // 3. Crear razas asociadas a especies
        for (int i = 0; i < 5; i++) {
            Raza raza = new Raza();
            raza.setNombre(faker.pokemon().name());
            raza.setEspecie(especies.get(random.nextInt(especies.size())));
            razaRepository.save(raza);
        }

        List<Raza> razas = razaRepository.findAll();

        // 4. Crear usuarios
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.name().firstName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setClave("123456"); // O encripta si es necesario
            usuario.setTelefono(faker.phoneNumber().cellPhone().replaceAll("[^\\d]", "").substring(0, 9));
            usuario.setDireccion(faker.address().streetAddress());
            usuario.setTipo(random.nextBoolean() ? "Cliente" : "Administrador");
            usuario.setFechaRegistro(new Date());
            usuario.setComuna(comunas.get(random.nextInt(comunas.size())));
            usuarioRepository.save(usuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();

        // 5. Crear animales
        for (int i = 0; i < 30; i++) {
            Animal animal = new Animal();
            animal.setNombre(faker.funnyName().name());
            animal.setEdad(random.nextInt(15));
            animal.setSexo(random.nextBoolean()  ? "m" :"f2");
            animal.setFechaRegistro(new Date());
            animal.setRaza(razas.get(random.nextInt(razas.size())));
            animal.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            animalRepository.save(animal);
        }

        List<Animal> animales = animalRepository.findAll();

        // 6. Crear vistas
        for (int i = 0; i < 50; i++) {
            Vistas vista = new Vistas();
            vista.setMatchStatus(random.nextBoolean() ? "Y" : "N");
            vista.setLike1(random.nextBoolean() ? random.nextInt(100) : null);
            vista.setLike2(random.nextBoolean() ? random.nextInt(100) : null);
            vista.setAnimal(animales.get(random.nextInt(animales.size())));
            vistasRepository.save(vista);
        }

        System.out.println("Base de datos poblada exitosamente con datos de prueba.");
    }
}
