package com.skarx.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skarx.producto.model.Proveedor;

@Repository
public interface RepositoryProveedor extends JpaRepository<Proveedor, Long> {

    Proveedor crearProveedor(Proveedor proveedor);

    Proveedor findById(long id);

    Proveedor findByNombre(String nombre);

    List<Proveedor> obtenerProveedores();

}
