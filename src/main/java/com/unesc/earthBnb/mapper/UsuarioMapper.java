package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.UsuarioRequest;
import com.unesc.earthBnb.dto.UsuarioResponse;
import com.unesc.earthBnb.model.Usuarios;

public class UsuarioMapper {

    public static Usuarios toEntity(UsuarioRequest request) {
        return Usuarios.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .usuario(request.usuario())
                .telefone(request.telefone())
                .build();
    }

    public static UsuarioResponse toResponse(Usuarios usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getUsuario(),
                usuario.getTelefone(),
                usuario.getCriadoEm()
        );
    }

    public static void merge(Usuarios entity, UsuarioRequest req) {
        if (req.nome() != null) entity.setNome(req.nome());
        if (req.email() != null) entity.setEmail(req.email());
        if (req.senha() != null) entity.setSenha(req.senha());
        if (req.usuario() != null) entity.setUsuario(req.usuario());
        if (req.telefone() != null) entity.setTelefone(req.telefone());
    }

}
