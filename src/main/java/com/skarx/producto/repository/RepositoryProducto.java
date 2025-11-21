package com.skarx.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skarx.producto.model.Producto;

@Repository
public interface RepositoryProducto extends JpaRepository<Producto, Long> {

    Producto findByNombre(String nombre);

}
