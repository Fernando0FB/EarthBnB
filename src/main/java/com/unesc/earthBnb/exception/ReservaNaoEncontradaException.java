package com.unesc.earthBnb.exception;

public class ReservaNaoEncontradaException extends RuntimeException {
    public ReservaNaoEncontradaException(Long id) {
        super("Reserva não encontrada com ID: " + id);
    }
}
