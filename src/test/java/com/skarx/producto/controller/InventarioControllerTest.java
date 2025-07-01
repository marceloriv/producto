package com.skarx.producto.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

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
import com.skarx.producto.dto.RegistracionInventarioDto;
import com.skarx.producto.model.Inventario;
import com.skarx.producto.service.InventarioService;

@WebMvcTest(InventarioController.class)
class InventarioControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private InventarioService inventarioService;

        @Autowired
        private ObjectMapper objectMapper;

        private RegistracionInventarioDto registracionInventarioDto;
        private ApiRespuestaDto apiRespuestaDto;
        private Inventario inventario;

        @BeforeEach
        void setUp() {
                registracionInventarioDto = new RegistracionInventarioDto();
                registracionInventarioDto.setFechaActualizacion(LocalDate.now());
                registracionInventarioDto.setIdProveedor(1L);

                apiRespuestaDto = new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Inventario registrado exitosamente");

                inventario = new Inventario();
                inventario.setId(1L);
                inventario.setFechaActualizacion(LocalDate.now());
        }

        @Test
        void testRegistrarInventario_Exitoso() throws Exception {
                when(inventarioService.registrarInventario(any(RegistracionInventarioDto.class)))
                                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(apiRespuestaDto));

                mockMvc.perform(post("/api/v1/inventarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registracionInventarioDto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.mensaje").value("Inventario registrado exitosamente"));
        }

        @Test
        void testRegistrarInventario_DatosInvalidos() throws Exception {
                RegistracionInventarioDto dtoInvalido = new RegistracionInventarioDto();
                dtoInvalido.setIdProveedor(null); // ID de proveedor nulo

                mockMvc.perform(post("/api/v1/inventarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dtoInvalido)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testObtenerTodosLosInventarios_Exitoso() throws Exception {
                when(inventarioService.obtenerTodosLosInventarios())
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(get("/api/v1/inventarios"))
                                .andExpect(status().isOk());
        }

        @Test
        void testObtenerInventarioPorId_Exitoso() throws Exception {
                when(inventarioService.obtenerInventarioPorId(anyLong()))
                                .thenReturn(ResponseEntity.ok(inventario));

                mockMvc.perform(get("/api/v1/inventarios/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testActualizarInventario_Exitoso() throws Exception {
                when(inventarioService.actualizarInventario(anyLong(), any(RegistracionInventarioDto.class)))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(put("/api/v1/inventarios/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registracionInventarioDto)))
                                .andExpect(status().isOk());
        }

        @Test
        void testEliminarInventario_Exitoso() throws Exception {
                when(inventarioService.eliminarInventario(anyLong()))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(delete("/api/v1/inventarios/1"))
                                .andExpect(status().isOk());
        }
}
