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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_ID")
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String correo;

    @Column(length = 10, nullable = false)
    private String clave;

    @Column(length = 10, nullable = false)
    private String telefono;

    @Column(length = 50, nullable = false)
    private String direccion;

    @Column(length = 20, nullable = false)
    private String tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = true)
    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "COMUNA_id_comuna", nullable = false)
    private Comuna comuna;
}



