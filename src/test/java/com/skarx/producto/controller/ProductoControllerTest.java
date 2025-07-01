package com.skarx.producto.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.ApiRespuestaEstados;
import com.skarx.producto.dto.RegistracionProductoDto;
import com.skarx.producto.model.Producto;
import com.skarx.producto.service.ProductoService;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ProductoService productoService;

        @Autowired
        private ObjectMapper objectMapper;

        private RegistracionProductoDto registracionProductoDto;
        private ApiRespuestaDto apiRespuestaDto;
        private Producto producto;

        @BeforeEach
        void setUp() {
                registracionProductoDto = new RegistracionProductoDto();
                registracionProductoDto.setNombre("Producto Test");
                registracionProductoDto.setDescripcion("Descripción test");
                registracionProductoDto.setPrecio(100.0);
                registracionProductoDto.setStock(10);
                registracionProductoDto.setIdInventario(1L);

                apiRespuestaDto = new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Producto registrado exitosamente");

                producto = new Producto();
                producto.setId(1L);
                producto.setNombre("Producto Test");
                producto.setDescripcion("Descripción test");
                producto.setPrecio(100.0);
                producto.setStock(10);
        }

        @Test
        void testRegistrarProducto_Exitoso() throws Exception {
                when(productoService.registrarProducto(any(RegistracionProductoDto.class)))
                                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(apiRespuestaDto));

                mockMvc.perform(post("/api/v1/productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registracionProductoDto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.mensaje").value("Producto registrado exitosamente"));
        }

        @Test
        void testRegistrarProducto_DatosInvalidos() throws Exception {
                RegistracionProductoDto dtoInvalido = new RegistracionProductoDto();
                dtoInvalido.setNombre(""); // Nombre vacío
                dtoInvalido.setPrecio(-1.0); // Precio negativo

                mockMvc.perform(post("/api/v1/productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dtoInvalido)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testObtenerProductoPorId_Exitoso() throws Exception {
                when(productoService.obtenerProductoPorId(anyLong()))
                                .thenReturn(ResponseEntity.ok(producto));

                mockMvc.perform(get("/api/v1/productos/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testObtenerTodosLosProductos_Exitoso() throws Exception {
                when(productoService.obtenerTodosLosProductos())
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(get("/api/v1/productos"))
                                .andExpect(status().isOk());
        }

        @Test
        void testActualizarProducto_Exitoso() throws Exception {
                when(productoService.actualizarProducto(any(Producto.class)))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(put("/api/v1/productos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(producto)))
                                .andExpect(status().isOk());
        }

        @Test
        void testEliminarProducto_Exitoso() throws Exception {
                when(productoService.eliminarProducto(anyLong()))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(delete("/api/v1/productos/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testActualizarProductoPorId_Exitoso() throws Exception {
                when(productoService.actualizarProductoPorId(anyLong(), any(RegistracionProductoDto.class)))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(put("/api/v1/productos/1/actualizar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registracionProductoDto)))
                                .andExpect(status().isOk());
        }
}
