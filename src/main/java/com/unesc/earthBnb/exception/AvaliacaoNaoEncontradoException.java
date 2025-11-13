package com.unesc.earthBnb.exception;

import org.yaml.snakeyaml.events.Event;

public class AvaliacaoNaoEncontradoException extends RuntimeException {
    public AvaliacaoNaoEncontradoException(Long id) {
        super("Avaliação não encontrada com ID: " + id);
    }
}
