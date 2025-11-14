package com.unesc.earthBnb.dto.response;

import com.unesc.earthBnb.model.Reservas;

import java.time.LocalDateTime;

public record AvaliacaoResponse(
        Long id,
        Reservas reserva,
        Integer nota,
        String comentario,
        LocalDateTime dataAvaliacao
) {
}
