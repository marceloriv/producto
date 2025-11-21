package com.skarx.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.ApiRespuestaEstados;
import com.skarx.producto.dto.RegistracionInventarioDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Inventario;
import com.skarx.producto.model.Producto;
import com.skarx.producto.model.Proveedor;
import com.skarx.producto.repository.RepositoryInventario;
import com.skarx.producto.repository.RepositoryProducto;
import com.skarx.producto.repository.RepositoryProveedor;

@Service
public class InventarioServiceImp implements InventarioService {

    @Autowired
    private RepositoryInventario repositoryInventario;

    @Autowired
    private RepositoryProveedor repositoryProveedor;

    @Autowired
    private RepositoryProducto repositoryProducto;

    @Override
    public ResponseEntity<ApiRespuestaDto> registrarInventario(RegistracionInventarioDto inventarioDto)
            throws MensajeException {
        try {
            // Buscar el proveedor
            Proveedor proveedor = repositoryProveedor.findById(inventarioDto.getIdProveedor())
                    .orElseThrow(() -> new MensajeException(
                            "Proveedor no encontrado con ID: " + inventarioDto.getIdProveedor()));

            // Crear el inventario a partir del DTO
            Inventario inventario = inventarioDto.convertirDtoAInventario();
            inventario.setProveedor(proveedor);

            // Asociar productos si se proporcionan
            if (inventarioDto.getIdsProductos() != null && !inventarioDto.getIdsProductos().isEmpty()) {
                List<Producto> productos = repositoryProducto.findAllById(inventarioDto.getIdsProductos());
                productos.forEach(producto -> producto.setInventario(inventario));
                inventario.setProductos(productos);
            }

            repositoryInventario.save(inventario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Inventario registrado exitosamente"));
        } catch (Exception e) {
            throw new MensajeException("Error al registrar el inventario: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerTodosLosInventarios() throws MensajeException {
        try {
            List<Inventario> inventarios = repositoryInventario.findAll();
            if (inventarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "No se encontraron inventarios"));
            }
            return ResponseEntity.ok(inventarios);
        } catch (Exception e) {
            throw new MensajeException("Error al obtener los inventarios: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerInventarioPorId(Long id) throws MensajeException {
        try {
            Inventario inventario = repositoryInventario.findById(id)
                    .orElseThrow(() -> new MensajeException("Inventario no encontrado con ID: " + id));
            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            throw new MensajeException("Error al obtener el inventario: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiRespuestaDto> eliminarInventario(Long id) throws MensajeException {
        try {
            Inventario inventario = repositoryInventario.findById(id)
                    .orElseThrow(() -> new MensajeException("Inventario no encontrado con ID: " + id));
            repositoryInventario.delete(inventario);
            return ResponseEntity
                    .ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Inventario eliminado exitosamente"));
        } catch (Exception e) {
            throw new MensajeException("Error al eliminar el inventario: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiRespuestaDto> actualizarInventario(Long id, RegistracionInventarioDto inventarioDto)
            throws MensajeException {
        try {
            Inventario inventario = repositoryInventario.findById(id)
                    .orElseThrow(() -> new MensajeException("Inventario no encontrado con ID: " + id));

            // Actualizar la fecha de actualización
            inventario.setFechaActualizacion(inventarioDto.getFechaActualizacion());

            // Actualizar el proveedor
            Proveedor proveedor = repositoryProveedor.findById(inventarioDto.getIdProveedor())
                    .orElseThrow(() -> new MensajeException(
                            "Proveedor no encontrado con ID: " + inventarioDto.getIdProveedor()));
            inventario.setProveedor(proveedor);

            // Actualizar los productos asociados
            if (inventarioDto.getIdsProductos() != null && !inventarioDto.getIdsProductos().isEmpty()) {
                List<Producto> productos = repositoryProducto.findAllById(inventarioDto.getIdsProductos());
                productos.forEach(producto -> producto.setInventario(inventario));
                inventario.setProductos(productos);
            }

            repositoryInventario.save(inventario);

            return ResponseEntity
                    .ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Inventario actualizado exitosamente"));
        } catch (Exception e) {
            throw new MensajeException("Error al actualizar el inventario: " + e.getMessage());
        }
    }

    @Override
    public Inventario consultarStock(Long id) throws MensajeException {
        try {
            Inventario inventario = repositoryInventario.findById(id)
                    .orElseThrow(() -> new MensajeException("Inventario no encontrado con ID: " + id));
            return inventario;
        } catch (Exception e) {
            throw new MensajeException("Error al consultar el stock del inventario: " + e.getMessage());
        }
    }
}
