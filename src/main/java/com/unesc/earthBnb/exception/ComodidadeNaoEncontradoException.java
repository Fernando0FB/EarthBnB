package com.unesc.earthBnb.exception;

public class ComodidadeNaoEncontradoException extends RuntimeException {
    public ComodidadeNaoEncontradoException(Long id) {
        super("Comodidade não encontrado com ID: " + id);
    }
}
