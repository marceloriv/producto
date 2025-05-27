package com.skarx.producto.dto;

import com.skarx.producto.model.Proveedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistracionProveedorDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    @Size(max = 100, message = "La dirección no puede tener más de 100 caracteres")
    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    private String telefono;

    public Proveedor convertirDtoAProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(this.nombre);
        proveedor.setDireccion(this.direccion);
        proveedor.setTelefono(this.telefono);
        return proveedor;
    }
}
