package com.skarx.producto.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionProductoDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Producto;

@Service
public interface ProductoService {

    ResponseEntity<ApiRespuestaDto> registrarProducto(RegistracionProductoDto nuevoProductoDto)
            throws MensajeException;

    ResponseEntity<Object> obtenerTodosLosProductos()
            throws MensajeException;

    ResponseEntity<Object> obtenerProductoPorId(Long id)
            throws MensajeException;

    ResponseEntity<Object> actualizarProducto(Producto productoActualizado)
            throws MensajeException;

    ResponseEntity<Object> eliminarProducto(Long id)
            throws MensajeException;

    ResponseEntity<Object> actualizarProductoPorId(Long id, RegistracionProductoDto productoDto)
            throws MensajeException;

    ResponseEntity<Object> consultarStock(Long id)
            throws MensajeException;

}
