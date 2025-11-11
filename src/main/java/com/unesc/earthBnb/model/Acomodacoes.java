package com.unesc.earthBnb.model;

import com.unesc.earthBnb.enums.TipoAcomodacoes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_acomodacoes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Acomodacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name="preco_diaria", nullable = false)
    private BigDecimal precoDiaria;

    @Column(name="endereco_completo", nullable = false, length = 255)
    private String enderecoCompleto;

    @Column(name = "max_hospedes", nullable= false)
    private Integer maxHospedes;

    @Column(name = "quantidade_quartos", nullable= false)
    private Integer quantidadeQuartos;

    @Column(name = "quantidade_banheiros", nullable= false)
    private Integer quantidadeBanheiros;

    @Column(name = "aceita_pets", nullable= false)
    private Boolean aceitaPets;


    @ManyToMany
    @JoinTable(
            name = "comodidades_acomodacoes",
            joinColumns = @JoinColumn(name = "acomodacao_id"),
            inverseJoinColumns = @JoinColumn(name = "comodidade_id")
    )
    private List<Comodidades> comodidades = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acomodacao", nullable = false, length = 20)
    private TipoAcomodacoes tipoAcomodacao;

}
