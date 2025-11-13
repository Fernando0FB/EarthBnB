package com.unesc.earthBnb.dto;

import com.unesc.earthBnb.model.Reservas;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record AvaliacaoRequest(
        @NotBlank(message = "Reserva é obrigatório")
        Reservas reserva,

        @NotBlank(message = "Nota é obrigatório")
        Integer nota,

        @Size(max = 80, message = "Comentário deve ter no máximo 80 caracteres")
        String comentario,

        @NotBlank(message = "Data é obrigatório")
        LocalDateTime dataAvaliacao
) {
}

