package com.skarx.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.ApiRespuestaEstados;
import com.skarx.producto.dto.RegistracionProveedorDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Proveedor;
import com.skarx.producto.repository.RepositoryProveedor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProveedorServiceImp implements ProveedorService {

    @Autowired
    private RepositoryProveedor repositoryProveedor;

    @Override
    public ResponseEntity<ApiRespuestaDto> registrarProveedor(RegistracionProveedorDto nuevoProveedorDto)
            throws MensajeException {
        try {
            if (repositoryProveedor.findByNombre(nuevoProveedorDto.getNombre()) != null) {
                throw new MensajeException("El proveedor ya existe con el nombre: " + nuevoProveedorDto.getNombre());
            }

            Proveedor nuevoProveedor = nuevoProveedorDto.convertirDtoAProveedor();
            repositoryProveedor.save(nuevoProveedor);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Proveedor registrado exitosamente"));
        } catch (MensajeException e) {
            throw new MensajeException(e.getMessage());
        } catch (Exception e) {
            throw new MensajeException("Error al registrar el proveedor: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerTodosLosProveedores() throws MensajeException {
        try {
            List<Proveedor> proveedores = repositoryProveedor.findAll();
            if (proveedores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "No se encontraron proveedores"));
            }
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            throw new MensajeException("Error al obtener los proveedores: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerProveedorPorId(Long id) throws MensajeException {
        try {
            Optional<Proveedor> proveedor = repositoryProveedor.findById(id);
            if (proveedor.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "Proveedor no encontrado con el ID: " + id));
            }
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            throw new MensajeException("Error al obtener el proveedor: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> actualizarProveedor(Long id, Proveedor proveedorActualizado)
            throws MensajeException {
        try {
            Proveedor proveedor = repositoryProveedor.findById(id).orElse(null);
            if (proveedor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "Proveedor no encontrado con el ID: " + id));
            }
            proveedorActualizado.setId(id);
            repositoryProveedor.save(proveedorActualizado);
            return ResponseEntity.ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Proveedor actualizado exitosamente"));
        } catch (Exception e) {
            throw new MensajeException("Error al actualizar el proveedor: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> eliminarProveedor(Long id) throws MensajeException {
        try {
            Proveedor proveedor = repositoryProveedor.findById(id).orElse(null);
            if (proveedor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "Proveedor no encontrado con el ID: " + id));
            }
            repositoryProveedor.delete(proveedor);
            return ResponseEntity.ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Proveedor eliminado exitosamente"));
        } catch (Exception e) {
            throw new MensajeException("Error al eliminar el proveedor: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> actualizarProveedorPorId(Long id, RegistracionProveedorDto proveedorDto)
            throws MensajeException {
        try {
            Proveedor proveedor = repositoryProveedor.findById(id).orElse(null);
            if (proveedor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "Proveedor no encontrado con el ID: " + id));
            }
            if (!proveedor.getNombre().equals(proveedorDto.getNombre())
                    && repositoryProveedor.findByNombre(proveedorDto.getNombre()) != null) {
                throw new MensajeException("El nombre ya está en uso: " + proveedorDto.getNombre());
            }
            Proveedor proveedorActualizado = proveedorDto.convertirDtoAProveedor();
            proveedorActualizado.setId(id);
            repositoryProveedor.save(proveedorActualizado);
            return ResponseEntity.ok(new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Proveedor actualizado exitosamente"));
        } catch (MensajeException e) {
            throw new MensajeException(e.getMessage());
        } catch (Exception e) {
            throw new MensajeException("Error al actualizar el proveedor: " + e.getMessage());
        }
    }
}
