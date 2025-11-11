package com.unesc.earthBnb.enums;

public enum TipoAcomodacoes {
    APARTAMENTO("Apartamento"),
    CASA("Casa"),
    QUARTO("Quarto"),
    SITIO("Sítio");

    private final String descricao;

    TipoAcomodacoes(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}