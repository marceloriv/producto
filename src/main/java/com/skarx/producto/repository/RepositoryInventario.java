package com.skarx.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skarx.producto.model.Inventario;

/**
 * Repositorio para la entidad Inventario. Proporciona métodos CRUD y consultas
 * personalizadas para la base de datos.
 */
@Repository
public interface RepositoryInventario extends JpaRepository<Inventario, Long> {

    @Query("SELECT i FROM Inventario i JOIN i.productos p WHERE p.id = :productoId")
    Inventario findByProductoId(@Param("productoId") long productoId);

}
