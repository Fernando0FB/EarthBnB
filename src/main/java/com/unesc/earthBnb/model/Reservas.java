package com.unesc.earthBnb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reservas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "acomodacao_id", nullable = false)
    private Acomodacoes acomodacoes;

    @Column(name = "data_checkin", nullable = false)
    private LocalDate dataCheckin;

    @Column(name = "data_checkout", nullable = false)
    private LocalDate dataCheckout;

    @Column(name = "quantidade_diarias", nullable = false)
    private Integer quantidadeDiarias;

    @Column(name = "valor_pagamento", nullable = false)
    private BigDecimal valorPagamento;

    @Column(name = "data_reserva", nullable = false)
    private LocalDateTime dataReserva;

}
