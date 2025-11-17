package com.unesc.earthBnb.exception;

public class ExisteReservasConcomitantesException extends RuntimeException {
    public ExisteReservasConcomitantesException() {
        super("Já existe uma reserva para esta acomodação no período selecionado");
    }
}
