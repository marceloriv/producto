package com.skarx.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skarx.producto.model.Proveedor;

@Repository
public interface RepositoryProveedor extends JpaRepository<Proveedor, Long> {

    Proveedor findByNombre(String nombre);

}
