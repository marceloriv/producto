package com.skarx.producto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock = 0; // Default value

    @Column(length = 50)
    private String categoria;

    @Column(length = 50) // Opcional para alinearse con frontend
    private String marca;

    @Column(length = 50)
    private String modelo;

    @Column(length = 255) // Campo para la ruta de la imagen del producto
    private String image;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_inventario", nullable = false)
    private Inventario inventario;
}
