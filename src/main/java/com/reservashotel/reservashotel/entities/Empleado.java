package com.reservashotel.reservashotel.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    private String nombre;
    private String apellido;
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}