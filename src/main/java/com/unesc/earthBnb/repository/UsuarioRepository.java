package com.unesc.earthBnb.repository;

import com.unesc.earthBnb.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findUsuariosByUsuario(String usuario);
}
