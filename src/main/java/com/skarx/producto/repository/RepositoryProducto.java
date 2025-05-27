package com.skarx.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skarx.producto.model.Producto;
import java.util.List;

@Repository
public interface RepositoryProducto extends JpaRepository<Producto, Long> {

    Producto crearProducto(Producto producto);

    Producto findById(long id);

    Producto findByNombre(String nombre);

    List<Producto> obtenerProductos();

}
