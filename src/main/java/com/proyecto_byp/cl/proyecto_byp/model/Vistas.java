package com.proyecto_byp.cl.proyecto_byp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vistas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vista")
    private Long id;

    @Column(length = 1, nullable = false)
    private String matchStatus;

    @Column(nullable = true)
    private Integer like1;

    @Column(nullable = true)
    private Integer like2;

    @ManyToOne
    @JoinColumn(name = "ANIMAL_id_animal", nullable = false)
    private Animal animal;
}
