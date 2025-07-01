package com.skarx.producto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.ApiRespuestaEstados;
import com.skarx.producto.dto.RegistracionProductoDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Inventario;
import com.skarx.producto.model.Producto;
import com.skarx.producto.repository.RepositoryInventario;
import com.skarx.producto.repository.RepositoryProducto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private RepositoryProducto repositoryProducto;

    @Autowired
    private RepositoryInventario repositoryInventario;

    @Override
    public ResponseEntity<ApiRespuestaDto> registrarProducto(RegistracionProductoDto nuevoProductoDto)
            throws MensajeException {
        try {
            if (repositoryProducto.findByNombre(nuevoProductoDto.getNombre()) != null) {
                throw new MensajeException("El producto ya existe con el nombre: " + nuevoProductoDto.getNombre());
            }

            // Buscar el inventario por ID
            Inventario inventario = repositoryInventario.findById(nuevoProductoDto.getIdInventario())
                    .orElseThrow(() -> new MensajeException("Inventario no encontrado con ID: " + nuevoProductoDto.getIdInventario()));
            //  Convertir el DTO a entidad Producto
            Producto nuevoProducto = nuevoProductoDto.convertirDtoAProducto();
            nuevoProducto.setInventario(inventario); // Asociar el producto con el inventario

            repositoryProducto.save(nuevoProducto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Producto registrado exitosamente"));
        } catch (MensajeException e) {
            throw e; // Re-lanza la excepción sin envolver
        } catch (Exception e) {
            throw new MensajeException("Error al registrar el producto: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerProductoPorId(Long id) throws MensajeException {
        try {
            Producto producto = repositoryProducto.findById(id)
                    .orElseThrow(() -> new MensajeException("Producto no encontrado con el ID: " + id));
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("producto", producto);
            respuesta.put("inventario", producto.getInventario().getId());
            return ResponseEntity.ok(respuesta);

        } catch (MensajeException e) {
            throw e; // Re-lanza la excepción sin envolver
        } catch (Exception e) {
            throw new MensajeException("Error al obtener el producto: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerTodosLosProductos() throws MensajeException {
        try {
            List<Producto> productos = repositoryProducto.findAll();
            if (productos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "No se encontraron productos"));
            }
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            throw new MensajeException("Error al obtener todos los productos: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> actualizarProducto(Producto productoActualizado) throws MensajeException {
        try {
            // Verificar que el producto existe
            if (productoActualizado.getId() <= 0) {
                throw new MensajeException("ID del producto no válido");
            }

            Producto producto = repositoryProducto.findById(productoActualizado.getId())
                    .orElseThrow(() -> new MensajeException("Producto no encontrado con el ID: " + productoActualizado.getId()));

            repositoryProducto.save(productoActualizado);
            return ResponseEntity.ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Producto actualizado exitosamente"));
        } catch (MensajeException e) {
            throw e; // Re-lanza la excepción sin envolver
        } catch (Exception e) {
            throw new MensajeException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> eliminarProducto(Long id) throws MensajeException {
        try {
            Producto producto = repositoryProducto.findById(id)
                    .orElseThrow(() -> new MensajeException("Producto no encontrado con el ID: " + id));
            repositoryProducto.delete(producto);
            return ResponseEntity.ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Producto eliminado exitosamente"));
        } catch (MensajeException e) {
            throw e; // Re-lanza la excepción sin envolver
        } catch (Exception e) {
            throw new MensajeException("Error al eliminar el producto: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> actualizarProductoPorId(Long id, RegistracionProductoDto productoDto)
            throws MensajeException {
        try {
            Producto producto = repositoryProducto.findById(id)
                    .orElseThrow(() -> new MensajeException("Producto no encontrado con el ID: " + id));

            // Verificar si el nombre ya está en uso por otro producto
            if (!producto.getNombre().equals(productoDto.getNombre()) && repositoryProducto.findByNombre(productoDto.getNombre()) != null) {
                throw new MensajeException("El nombre ya está en uso: " + productoDto.getNombre());
            }

            Inventario inventario = repositoryInventario.findById(productoDto.getIdInventario())
                    .orElseThrow(() -> new MensajeException("Inventario no encontrado con ID: " + productoDto.getIdInventario()));

            Producto productoActualizado = productoDto.convertirDtoAProducto();
            productoActualizado.setId(id); // CORREGIDO: Establecer el ID del producto
            productoActualizado.setInventario(inventario);

            repositoryProducto.save(productoActualizado);
            return ResponseEntity.ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Producto actualizado exitosamente"));
        } catch (MensajeException e) {
            throw e; // Re-lanza la excepción sin envolver
        } catch (Exception e) {
            throw new MensajeException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> consultarStock(Long id) throws MensajeException {
        try {
            Producto producto = repositoryProducto.findById(id)
                    .orElseThrow(() -> new MensajeException("Producto no encontrado con el ID: " + id));
            int stock = producto.getStock();
            return ResponseEntity.ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Stock disponible: " + stock));
        } catch (MensajeException e) {
            throw e; // Re-lanza la excepción sin envolver
        } catch (Exception e) {
            throw new MensajeException("Error al consultar el stock del producto: " + e.getMessage());
        }
    }
}
