package com.skarx.producto.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.RegistracionInventarioDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Inventario;
import com.skarx.producto.model.Proveedor;
import com.skarx.producto.repository.RepositoryInventario;
import com.skarx.producto.repository.RepositoryProveedor;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private RepositoryInventario repositoryInventario;

    @Mock
    private RepositoryProveedor repositoryProveedor;

    @InjectMocks
    private InventarioServiceImp inventarioService;

    private RegistracionInventarioDto registracionInventarioDto;
    private Inventario inventario;
    private Proveedor proveedor;

    @BeforeEach
    void setUp() {
        registracionInventarioDto = new RegistracionInventarioDto();
        registracionInventarioDto.setFechaActualizacion(LocalDate.now());
        registracionInventarioDto.setIdProveedor(1L);

        proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombre("Proveedor Test");

        inventario = new Inventario();
        inventario.setId(1L);
        inventario.setFechaActualizacion(LocalDate.now());
        inventario.setProveedor(proveedor);
    }

    @Test
    void testRegistrarInventario_Exitoso() throws MensajeException {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.of(proveedor));
        when(repositoryInventario.save(any(Inventario.class))).thenReturn(inventario);

        // When
        ResponseEntity<ApiRespuestaDto> response = inventarioService.registrarInventario(registracionInventarioDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(repositoryInventario).save(any(Inventario.class));
    }

    @Test
    void testRegistrarInventario_ProveedorNoEncontrado() {
        // Given
        when(repositoryProveedor.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, ()
                -> inventarioService.registrarInventario(registracionInventarioDto));
    }

    @Test
    void testObtenerTodosLosInventarios_Exitoso() throws MensajeException {
        // Given
        List<Inventario> inventarios = Arrays.asList(inventario);
        when(repositoryInventario.findAll()).thenReturn(inventarios);

        // When
        ResponseEntity<Object> response = inventarioService.obtenerTodosLosInventarios();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryInventario).findAll();
    }

    @Test
    void testObtenerInventarioPorId_Exitoso() throws MensajeException {
        // Given
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.of(inventario));

        // When
        ResponseEntity<Object> response = inventarioService.obtenerInventarioPorId(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryInventario).findById(any(Long.class));
    }

    @Test
    void testObtenerInventarioPorId_NoEncontrado() {
        // Given
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, ()
                -> inventarioService.obtenerInventarioPorId(1L));
    }

    @Test
    void testEliminarInventario_Exitoso() throws MensajeException {
        // Given
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.of(inventario));

        // When
        ResponseEntity<ApiRespuestaDto> response = inventarioService.eliminarInventario(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryInventario).delete(any(Inventario.class));
    }

    @Test
    void testEliminarInventario_NoEncontrado() {
        // Given
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, ()
                -> inventarioService.eliminarInventario(1L));
    }

    @Test
    void testConsultarStock_Exitoso() throws MensajeException {
        // Given
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.of(inventario));

        // When
        Inventario result = inventarioService.consultarStock(1L);

        // Then
        assertNotNull(result);
        assertEquals(inventario.getId(), result.getId());
        verify(repositoryInventario).findById(any(Long.class));
    }

    @Test
    void testConsultarStock_NoEncontrado() {
        // Given
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, ()
                -> inventarioService.consultarStock(1L));
    }
}
