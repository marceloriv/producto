package com.skarx.producto.service;

import org.springframework.http.ResponseEntity;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionInventarioDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Inventario;

public interface InventarioService {

    /**
     * Registra un nuevo inventario en el sistema.
     *
     * @param inventarioDto DTO con los datos del inventario.
     * @return Respuesta con el estado de la operación.
     * @throws MensajeException Si ocurre un error durante el registro.
     */
    ResponseEntity<ApiRespuestaDto> registrarInventario(RegistracionInventarioDto inventarioDto)
            throws MensajeException;

    /**
     * Obtiene todos los inventarios registrados en el sistema.
     *
     * @return Lista de inventarios.
     * @throws MensajeException Si ocurre un error durante la consulta.
     */
    ResponseEntity<Object> obtenerTodosLosInventarios() throws MensajeException;

    /**
     * Obtiene un inventario por su ID.
     *
     * @param id ID del inventario.
     * @return Inventario encontrado.
     * @throws MensajeException Si no se encuentra el inventario.
     */
    ResponseEntity<Object> obtenerInventarioPorId(Long id) throws MensajeException;

    /**
     * Elimina un inventario por su ID.
     *
     * @param id ID del inventario.
     * @return Respuesta con el estado de la operación.
     * @throws MensajeException Si ocurre un error durante la eliminación.
     */
    ResponseEntity<ApiRespuestaDto> eliminarInventario(Long id) throws MensajeException;

    /**
     * Actualiza un inventario existente.
     *
     * @param id            ID del inventario a actualizar.
     * @param inventarioDto DTO con los nuevos datos del inventario.
     * @return Respuesta con el estado de la operación.
     * @throws MensajeException Si ocurre un error durante la actualización.
     */
    ResponseEntity<ApiRespuestaDto> actualizarInventario(Long id, RegistracionInventarioDto inventarioDto)
            throws MensajeException;

    /**
     * Consulta el stock de un inventario por su ID.
     *
     * @param id ID del inventario.
     * @return Inventario encontrado.
     * @throws MensajeException Si no se encuentra el inventario.
     */
    Inventario consultarStock(Long id) throws MensajeException;

}
