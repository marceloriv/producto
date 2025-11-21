package com.skarx.producto.handler;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.skarx.producto.dto.ApiRespuestaDto;
import com.skarx.producto.dto.ApiRespuestaEstados;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class InventarioExceptionHandler {

    /*
     * Metodo para manejar excepciones de tipo MethodArgumentNotValidException.
     * Esta excepción se lanza cuando hay errores de validación en los argumentos
     * del método.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiRespuestaDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String mensaje = "Error de validación en el módulo de inventario";
        var fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null) {
            // Obtiene el mensaje de error del campo específico que falló la validación
            mensaje = Objects.requireNonNullElse(
                    fieldError.getDefaultMessage(),
                    "Error de validación en el módulo de inventario");
        }
        return ResponseEntity.badRequest()
                .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, mensaje));
    }

    /*
     * Metodo para manejar excepciones genéricas.
     * Esta excepción se lanza cuando ocurre un error inesperado en el módulo de
     * inventario.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiRespuestaDto> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiRespuestaDto(ApiRespuestaEstados.ERROR, "Error interno en el módulo de inventario"));
    }
}
