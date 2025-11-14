package com.unesc.earthBnb.exception;

public class AcomodacaoNaoEncontradaException extends RuntimeException {
    public AcomodacaoNaoEncontradaException(Long id) {
        super("Acomodação não encontrada com ID: " + id);
    }
}
