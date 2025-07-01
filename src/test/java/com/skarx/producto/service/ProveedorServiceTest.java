package com.skarx.producto.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionProveedorDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Proveedor;
import com.skarx.producto.repository.RepositoryProveedor;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private RepositoryProveedor repositoryProveedor;

    @InjectMocks
    private ProveedorServiceImp proveedorService;

    private RegistracionProveedorDto registracionProveedorDto;
    private Proveedor proveedor;

    @BeforeEach
    void setUp() {
        registracionProveedorDto = new RegistracionProveedorDto();
        registracionProveedorDto.setNombre("Proveedor Test");
        registracionProveedorDto.setDireccion("Dirección test");
        registracionProveedorDto.setTelefono("12345678901");

        proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombre("Proveedor Test");
        proveedor.setDireccion("Dirección test");
        proveedor.setTelefono("12345678901");
    }

    @Test
    void testRegistrarProveedor_Exitoso() throws MensajeException {
        // Given
        when(repositoryProveedor.findByNombre(anyString())).thenReturn(null);
        when(repositoryProveedor.save(any(Proveedor.class))).thenReturn(proveedor);

        // When
        ResponseEntity<ApiRespuestaDto> response = proveedorService.registrarProveedor(registracionProveedorDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(repositoryProveedor).save(any(Proveedor.class));
    }

    @Test
    void testRegistrarProveedor_ProveedorYaExiste() {
        // Given
        when(repositoryProveedor.findByNombre(anyString())).thenReturn(proveedor);

        // When & Then
        assertThrows(MensajeException.class, ()
                -> proveedorService.registrarProveedor(registracionProveedorDto));
    }

    @Test
    void testObtenerTodosLosProveedores_Exitoso() throws MensajeException {
        // Given
        List<Proveedor> proveedores = Arrays.asList(proveedor);
        when(repositoryProveedor.findAll()).thenReturn(proveedores);

        // When
        ResponseEntity<Object> response = proveedorService.obtenerTodosLosProveedores();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryProveedor).findAll();
    }

    @Test
    void testObtenerProveedorPorId_Exitoso() throws MensajeException {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.of(proveedor));

        // When
        ResponseEntity<Object> response = proveedorService.obtenerProveedorPorId(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryProveedor).findById(any(Long.class));
    }

    @Test
    void testObtenerProveedorPorId_NoEncontrado() throws MensajeException {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.empty());

        // When
        ResponseEntity<Object> response = proveedorService.obtenerProveedorPorId(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testEliminarProveedor_Exitoso() throws MensajeException {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.of(proveedor));

        // When
        ResponseEntity<Object> response = proveedorService.eliminarProveedor(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryProveedor).delete(any(Proveedor.class));
    }

    @Test
    void testEliminarProveedor_NoEncontrado() throws MensajeException {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.empty());

        // When
        ResponseEntity<Object> response = proveedorService.eliminarProveedor(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testActualizarProveedor_Exitoso() throws MensajeException {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.of(proveedor));
        when(repositoryProveedor.save(any(Proveedor.class))).thenReturn(proveedor);

        // When
        ResponseEntity<Object> response = proveedorService.actualizarProveedor(1L, proveedor);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryProveedor).save(any(Proveedor.class));
    }

    @Test
    void testActualizarProveedor_NoEncontrado() throws MensajeException {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.empty());

        // When
        ResponseEntity<Object> response = proveedorService.actualizarProveedor(1L, proveedor);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
