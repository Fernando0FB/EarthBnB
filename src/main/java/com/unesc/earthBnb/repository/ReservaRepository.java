package com.unesc.earthBnb.repository;

import com.unesc.earthBnb.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reservas, Long> {
    @Query("""
        SELECT r FROM Reservas r
        WHERE r.acomodacoes.id = :acomodacaoId
          AND (
              ((r.dataCheckin <= :dataCheckin) AND (r.dataCheckout >= :dataCheckin))
                  OR 
              ((r.dataCheckin <= :dataCheckout) AND (r.dataCheckout >= :dataCheckout))
                  OR
              ((r.dataCheckin >= :dataCheckin) AND (r.dataCheckout <= :dataCheckout))
          )
    """)
    List<Reservas> findConflitos(
            @Param("acomodacaoId") Long acomodacaoId,
            @Param("dataCheckin") LocalDate dataCheckin,
            @Param("dataCheckout") LocalDate dataCheckout
    );
}
