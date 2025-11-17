package com.unesc.earthBnb.repository;

import com.unesc.earthBnb.model.Acomodacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcomodacaoRepository extends JpaRepository<Acomodacoes, Long> {
    Long id(Long id);
}
