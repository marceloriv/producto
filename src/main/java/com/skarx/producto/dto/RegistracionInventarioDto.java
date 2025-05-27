package com.skarx.producto.dto;

import java.time.LocalDate;
import java.util.List;

import com.skarx.producto.model.Inventario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistracionInventarioDto {

    @NotNull(message = "La fecha de actualización no puede estar vacía")
    private LocalDate fechaActualizacion;

    @NotNull(message = "El ID del proveedor no puede estar vacío")
    private Long idProveedor;

    private List<Long> idsProductos;

    public Inventario convertirDtoAInventario() {
        Inventario inventario = new Inventario();
        inventario.setFechaActualizacion(this.fechaActualizacion);
        return inventario;
    }
}
