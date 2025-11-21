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
import com.skarx.producto.dto.RegistracionProductoDto;
import com.skarx.producto.exception.MensajeException;
import com.skarx.producto.model.Inventario;
import com.skarx.producto.model.Producto;
import com.skarx.producto.repository.RepositoryInventario;
import com.skarx.producto.repository.RepositoryProducto;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private RepositoryProducto repositoryProducto;

    @Mock
    private RepositoryInventario repositoryInventario;

    @InjectMocks
    private ProductoServiceImp productoService;

    private RegistracionProductoDto registracionProductoDto;
    private Producto producto;
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        registracionProductoDto = new RegistracionProductoDto();
        registracionProductoDto.setNombre("Producto Test");
        registracionProductoDto.setDescripcion("Descripción test");
        registracionProductoDto.setPrecio(100.0);
        registracionProductoDto.setStock(10);
        registracionProductoDto.setIdInventario(1L);

        inventario = new Inventario();
        inventario.setId(1L);

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción test");
        producto.setPrecio(100.0);
        producto.setStock(10);
        producto.setInventario(inventario); // Importante: asociar el inventario
    }

    @Test
    void testRegistrarProducto_Exitoso() throws MensajeException {
        // Given
        when(repositoryProducto.findByNombre(anyString())).thenReturn(null);
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.of(inventario));
        when(repositoryProducto.save(any(Producto.class))).thenReturn(producto);

        // When
        ResponseEntity<ApiRespuestaDto> response = productoService.registrarProducto(registracionProductoDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(repositoryProducto).save(any(Producto.class));
    }

    @Test
    void testRegistrarProducto_ProductoYaExiste() {
        // Given
        when(repositoryProducto.findByNombre(anyString())).thenReturn(producto);

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.registrarProducto(registracionProductoDto));
    }

    @Test
    void testRegistrarProducto_InventarioNoEncontrado() {
        // Given
        when(repositoryProducto.findByNombre(anyString())).thenReturn(null);
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.registrarProducto(registracionProductoDto));
    }

    @Test
    void testObtenerProductoPorId_Exitoso() throws MensajeException {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.of(producto));

        // When
        ResponseEntity<Object> response = productoService.obtenerProductoPorId(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testObtenerProductoPorId_NoEncontrado() {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.obtenerProductoPorId(1L));
    }

    @Test
    void testObtenerTodosLosProductos_Exitoso() throws MensajeException {
        // Given
        List<Producto> productos = Arrays.asList(producto);
        when(repositoryProducto.findAll()).thenReturn(productos);

        // When
        ResponseEntity<Object> response = productoService.obtenerTodosLosProductos();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testEliminarProducto_Exitoso() throws MensajeException {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.of(producto));

        // When
        ResponseEntity<Object> response = productoService.eliminarProducto(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testEliminarProducto_NoEncontrado() {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.eliminarProducto(1L));
    }

    @Test
    void testActualizarProducto_Exitoso() throws MensajeException {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.of(producto));
        when(repositoryProducto.save(any(Producto.class))).thenReturn(producto);

        // When
        ResponseEntity<Object> response = productoService.actualizarProducto(producto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryProducto).save(any(Producto.class));
    }

    @Test
    void testActualizarProducto_IdNulo() {
        // Given - Ahora podemos probar con null ya que id es Long
        Producto productoConIdNulo = new Producto();
        productoConIdNulo.setId(null);

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.actualizarProducto(productoConIdNulo));
    }

    @Test
    void testActualizarProducto_IdInvalido() {
        // Given
        Producto productoConIdInvalido = new Producto();
        productoConIdInvalido.setId(0L);

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.actualizarProducto(productoConIdInvalido));
    }

    @Test
    void testActualizarProducto_NoEncontrado() {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.actualizarProducto(producto));
    }

    @Test
    void testActualizarProductoPorId_Exitoso() throws MensajeException {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.of(producto));
        when(repositoryInventario.findById(any(Long.class))).thenReturn(Optional.of(inventario));
        when(repositoryProducto.save(any(Producto.class))).thenReturn(producto);

        // When
        ResponseEntity<Object> response = productoService.actualizarProductoPorId(1L, registracionProductoDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryProducto).save(any(Producto.class));
    }

    @Test
    void testConsultarStock_Exitoso() throws MensajeException {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.of(producto));

        // When
        ResponseEntity<Object> response = productoService.consultarStock(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repositoryProducto).findById(any(Long.class));
    }

    @Test
    void testConsultarStock_NoEncontrado() {
        // Given
        when(repositoryProducto.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MensajeException.class, () -> productoService.consultarStock(1L));
    }
}
