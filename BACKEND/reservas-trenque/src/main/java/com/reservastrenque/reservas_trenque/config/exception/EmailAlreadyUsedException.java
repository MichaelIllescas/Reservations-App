package com.reservastrenque.reservas_trenque.config.exception;


public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("Ya existe un responsable con el email " + email);
    }
}
