package com.reservashotel.reservashotel.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
