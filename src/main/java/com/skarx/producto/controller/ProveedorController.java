package com.skarx.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionProveedorDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Proveedor;
import com.skarx.producto.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedores", description = "Operaciones relacionadas con la gestión de proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo proveedor", description = "Permite registrar un nuevo proveedor en el sistema")
    public ResponseEntity<ApiRespuestaDto> registrarProveedor(@Valid @RequestBody RegistracionProveedorDto nuevoProveedorDto)
            throws MensajeException {
        return proveedorService.registrarProveedor(nuevoProveedorDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description = "Permite obtener un proveedor a partir de su ID")
    public ResponseEntity<Object> obtenerProveedorPorId(@PathVariable Long id) throws MensajeException {
        return proveedorService.obtenerProveedorPorId(id);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description = "Permite obtener todos los proveedores registrados")
    public ResponseEntity<Object> obtenerTodosLosProveedores() throws MensajeException {
        return proveedorService.obtenerTodosLosProveedores();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Permite actualizar los datos de un proveedor existente")
    public ResponseEntity<Object> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorActualizado)
            throws MensajeException {
        return proveedorService.actualizarProveedor(id, proveedorActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Permite eliminar un proveedor a partir de su ID")
    public ResponseEntity<Object> eliminarProveedor(@PathVariable Long id) throws MensajeException {
        return proveedorService.eliminarProveedor(id);
    }

    @PutMapping("/{id}/actualizar")
    @Operation(summary = "Actualizar proveedor por ID", description = "Permite actualizar los datos de un proveedor a partir de su ID")
    public ResponseEntity<Object> actualizarProveedorPorId(@PathVariable Long id, @RequestBody RegistracionProveedorDto proveedorDto)
            throws MensajeException {
        return proveedorService.actualizarProveedorPorId(id, proveedorDto);
    }
}
