package com.compulandia.sistematickets.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tecnico {
    @Id
    private String id;

    private String nombre;
    private String apellido;
    
    @Column(unique = true)
    private String codigo;

    private String especialidad;

    private String foto;

}
