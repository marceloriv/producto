package com.skarx.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skarx.producto.model.Inventario;

/**
 * Repositorio para la entidad Inventario. Proporciona métodos CRUD y consultas
 * personalizadas para la base de datos.
 */
@Repository
public interface RepositoryInventario extends JpaRepository<Inventario, Long> {

    Inventario crearInventario(Inventario inventario);

    Inventario findById(long id);

    Inventario findByProductoId(long productoId);

    void deleteById(long id);

    Inventario updateInventario(Inventario inventario);

    List<Inventario> findAll();
}
