package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.UsuarioRequest;
import com.unesc.earthBnb.dto.response.UsuarioResponse;
import com.unesc.earthBnb.exception.AcomodacaoNaoEncontradaException;
import com.unesc.earthBnb.exception.UsuarioNaoEncontradoException;
import com.unesc.earthBnb.mapper.UsuarioMapper;
import com.unesc.earthBnb.model.Usuarios;
import com.unesc.earthBnb.repository.UsuarioRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRespository usuarioRepository;

    public UsuarioService(UsuarioRespository usuariosRespository) {
        this.usuarioRepository = usuariosRespository;
    }

    @Transactional(readOnly = true)
    public UsuarioResponse getUsuarioById(Long id) {
        Usuarios usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        return UsuarioMapper.toResponse(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException(id);
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioResponse updateUsuario(Long id, UsuarioRequest usuarioUpdate) {
        Usuarios usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        UsuarioMapper.merge(usuario, usuarioUpdate);
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }
}
