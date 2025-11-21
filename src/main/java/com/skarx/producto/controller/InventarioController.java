package com.skarx.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionInventarioDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Inventario;
import com.skarx.producto.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/inventarios")
@Tag(name = "Inventarios", description = "Operaciones relacionadas con la gestión de inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo inventario", description = "Permite registrar un nuevo inventario en el sistema")
    public ResponseEntity<ApiRespuestaDto> registrarInventario(
            @Valid @RequestBody RegistracionInventarioDto inventarioDto)
            throws MensajeException {
        return inventarioService.registrarInventario(inventarioDto);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los inventarios", description = "Permite obtener todos los inventarios registrados")
    public ResponseEntity<Object> obtenerTodosLosInventarios() throws MensajeException {
        return inventarioService.obtenerTodosLosInventarios();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inventario por ID", description = "Permite obtener un inventario a partir de su ID")
    public ResponseEntity<Object> obtenerInventarioPorId(@PathVariable Long id) throws MensajeException {
        return inventarioService.obtenerInventarioPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inventario", description = "Permite actualizar los datos de un inventario existente")
    public ResponseEntity<ApiRespuestaDto> actualizarInventario(@PathVariable Long id,
            @Valid @RequestBody RegistracionInventarioDto inventarioDto) throws MensajeException {
        return inventarioService.actualizarInventario(id, inventarioDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inventario", description = "Permite eliminar un inventario a partir de su ID")
    public ResponseEntity<ApiRespuestaDto> eliminarInventario(@PathVariable Long id) throws MensajeException {
        return inventarioService.eliminarInventario(id);
    }

    @GetMapping("/{id}/stock")
    @Operation(summary = "Consultar stock de un inventario", description = "Permite consultar el stock de un inventario por su ID")
    public ResponseEntity<Inventario> consultarStock(@PathVariable Long id) throws MensajeException {
        return ResponseEntity.ok(inventarioService.consultarStock(id));
    }
}
