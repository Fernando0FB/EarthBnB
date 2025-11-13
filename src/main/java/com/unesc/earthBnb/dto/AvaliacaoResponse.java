package com.unesc.earthBnb.dto;

import com.unesc.earthBnb.model.Reservas;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

public record AvaliacaoResponse(
        Long id,
        Reservas reserva,
        Integer nota,
        String comentario,
        LocalDateTime dataAvaliacao
) {
}
