package com.skarx.producto.dto;

import com.skarx.producto.model.Producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistracionProductoDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String descripcion;

    @NotNull(message = "El precio no puede estar vacío")
    @Positive(message = "El precio debe ser mayor a 0")
    private double precio;

    @NotNull(message = "El stock no puede estar vacío")
    @Positive(message = "El stock debe ser mayor o igual a 0")
    private int stock;

    private String categoria; // Opcional, puede ser null
    private String marca; // Opcional, puede ser null

    private String modelo; // Opcional, puede ser null
    private Long idInventario; // Opcional - se auto-creará si es null

    public Producto convertirDtoAProducto() {
        Producto producto = new Producto();
        producto.setNombre(this.nombre);
        producto.setDescripcion(this.descripcion);
        producto.setPrecio(this.precio);
        producto.setStock(this.stock);
        producto.setCategoria(this.categoria);
        producto.setMarca(this.marca);
        producto.setModelo(this.modelo);
        return producto;
    }
}
