package com.reservashotel.reservashotel.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long idHabitacion;

    private Integer numero;
    private Integer piso;

    @ManyToOne
    @JoinColumn(name = "id_tipo_habitacion")
    private TipoHabitacion tipoHabitacion;

    @Column(name = "estado", nullable = false)
    private String estado;
}