package com.skarx.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionProductoDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Producto;
import com.skarx.producto.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo producto", description = "Permite registrar un nuevo producto en el sistema")
    public ResponseEntity<ApiRespuestaDto> registrarProducto(@Valid @RequestBody RegistracionProductoDto nuevoProductoDto)
            throws MensajeException {
        return productoService.registrarProducto(nuevoProductoDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Permite obtener un producto a partir de su ID")
    public ResponseEntity<Object> obtenerProductoPorId(@PathVariable Long id) throws MensajeException {
        return productoService.obtenerProductoPorId(id);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Permite obtener todos los productos registrados")
    public ResponseEntity<Object> obtenerTodosLosProductos() throws MensajeException {
        return productoService.obtenerTodosLosProductos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Permite actualizar los datos de un producto existente")
    public ResponseEntity<Object> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado)
            throws MensajeException {
        return productoService.actualizarProducto(productoActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Permite eliminar un producto a partir de su ID")
    public ResponseEntity<Object> eliminarProducto(@PathVariable Long id) throws MensajeException {
        return productoService.eliminarProducto(id);
    }

    @PutMapping("/{id}/actualizar")
    @Operation(summary = "Actualizar producto por ID", description = "Permite actualizar los datos de un producto a partir de su ID")
    public ResponseEntity<Object> actualizarProductoPorId(@PathVariable Long id, @RequestBody RegistracionProductoDto productoDto)
            throws MensajeException {
        return productoService.actualizarProductoPorId(id, productoDto);
    }
}
