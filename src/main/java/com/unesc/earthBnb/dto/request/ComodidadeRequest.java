package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ComodidadeRequest(
        @Size(max = 30)
        @NotBlank(message = "Nome não pode ser vazio!")
        String nome
) {
}
