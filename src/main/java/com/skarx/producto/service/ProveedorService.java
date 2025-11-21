package com.skarx.producto.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionProveedorDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Proveedor;

@Service
public interface ProveedorService {

        ResponseEntity<ApiRespuestaDto> registrarProveedor(RegistracionProveedorDto nuevoProveedorDto)
                        throws MensajeException;

        ResponseEntity<Object> obtenerTodosLosProveedores()
                        throws MensajeException;

        ResponseEntity<Object> obtenerProveedorPorId(Long id)
                        throws MensajeException;

        ResponseEntity<Object> actualizarProveedor(Long id, Proveedor proveedorActualizado)
                        throws MensajeException;

        ResponseEntity<Object> eliminarProveedor(Long id)
                        throws MensajeException;

        ResponseEntity<Object> actualizarProveedorPorId(Long id, RegistracionProveedorDto proveedorDto)
                        throws MensajeException;

}
