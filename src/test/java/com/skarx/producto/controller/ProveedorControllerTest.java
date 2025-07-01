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
import com.skarx.producto.dto.RegistracionProveedorDto;
import com.skarx.producto.model.Proveedor;
import com.skarx.producto.service.ProveedorService;

@WebMvcTest(ProveedorController.class)
class ProveedorControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ProveedorService proveedorService;

        @Autowired
        private ObjectMapper objectMapper;

        private RegistracionProveedorDto registracionProveedorDto;
        private ApiRespuestaDto apiRespuestaDto;
        private Proveedor proveedor;

        @BeforeEach
        void setUp() {
                registracionProveedorDto = new RegistracionProveedorDto();
                registracionProveedorDto.setNombre("Proveedor Test");
                registracionProveedorDto.setDireccion("Dirección test");
                registracionProveedorDto.setTelefono("12345678901");

                apiRespuestaDto = new ApiRespuestaDto(ApiRespuestaEstados.EXITO, "Proveedor registrado exitosamente");

                proveedor = new Proveedor();
                proveedor.setId(1L);
                proveedor.setNombre("Proveedor Test");
                proveedor.setDireccion("Dirección test");
                proveedor.setTelefono("12345678901");
        }

        @Test
        void testRegistrarProveedor_Exitoso() throws Exception {
                when(proveedorService.registrarProveedor(any(RegistracionProveedorDto.class)))
                                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(apiRespuestaDto));

                mockMvc.perform(post("/api/v1/proveedores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registracionProveedorDto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.mensaje").value("Proveedor registrado exitosamente"));
        }

        @Test
        void testRegistrarProveedor_DatosInvalidos() throws Exception {
                RegistracionProveedorDto dtoInvalido = new RegistracionProveedorDto();
                dtoInvalido.setNombre(""); // Nombre vacío
                dtoInvalido.setTelefono("123"); // Teléfono muy corto

                mockMvc.perform(post("/api/v1/proveedores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dtoInvalido)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testObtenerProveedorPorId_Exitoso() throws Exception {
                when(proveedorService.obtenerProveedorPorId(anyLong()))
                                .thenReturn(ResponseEntity.ok(proveedor));

                mockMvc.perform(get("/api/v1/proveedores/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testObtenerTodosLosProveedores_Exitoso() throws Exception {
                when(proveedorService.obtenerTodosLosProveedores())
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(get("/api/v1/proveedores"))
                                .andExpect(status().isOk());
        }

        @Test
        void testActualizarProveedor_Exitoso() throws Exception {
                when(proveedorService.actualizarProveedor(anyLong(), any(Proveedor.class)))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(put("/api/v1/proveedores/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(proveedor)))
                                .andExpect(status().isOk());
        }

        @Test
        void testEliminarProveedor_Exitoso() throws Exception {
                when(proveedorService.eliminarProveedor(anyLong()))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(delete("/api/v1/proveedores/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testActualizarProveedorPorId_Exitoso() throws Exception {
                when(proveedorService.actualizarProveedorPorId(anyLong(), any(RegistracionProveedorDto.class)))
                                .thenReturn(ResponseEntity.ok().build());

                mockMvc.perform(put("/api/v1/proveedores/1/actualizar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registracionProveedorDto)))
                                .andExpect(status().isOk());
        }
}
