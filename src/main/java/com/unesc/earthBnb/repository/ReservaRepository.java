package com.unesc.earthBnb.repository;

import com.unesc.earthBnb.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reservas, Long> {
}
