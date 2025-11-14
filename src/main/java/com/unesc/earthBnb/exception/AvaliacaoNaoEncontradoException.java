package com.unesc.earthBnb.exception;

public class AvaliacaoNaoEncontradoException extends RuntimeException {
    public AvaliacaoNaoEncontradoException(Long id) {
        super("Avaliação não encontrada com ID: " + id);
    }
}
