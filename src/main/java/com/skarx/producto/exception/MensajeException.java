package com.skarx.producto.exception;

public class MensajeException extends Exception {

    public MensajeException(String mensaje) {
        super(mensaje);
    }

    public MensajeException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public MensajeException(Throwable causa) {
        super(causa);
    }

}
