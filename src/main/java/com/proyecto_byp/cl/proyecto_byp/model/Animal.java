package com.proyecto_byp.cl.proyecto_byp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMAL_ID")
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 10, nullable = false)
    private String sexo;

    @Column(nullable = false)
    private Integer edad;

    @Column(length = 100, nullable = true)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = true)
    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "RAZA_ID", nullable = false)
    private Raza raza;
}
