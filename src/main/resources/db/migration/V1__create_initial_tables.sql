-- Tabela de usuários
CREATE TABLE tb_usuarios (
                             id BIGSERIAL PRIMARY KEY,
                             nome VARCHAR(80) NOT NULL,
                             email VARCHAR(255) NOT NULL,
                             usuario VARCHAR(80) NOT NULL,
                             senha VARCHAR(80) NOT NULL,
                             telefone VARCHAR(15),
                             criado_em DATE
);

-- Tabela de roles
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role VARCHAR(255) NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES tb_usuarios(id)
);

-- Tabela de comodidades
CREATE TABLE tb_comodidades (
                                id BIGSERIAL PRIMARY KEY,
                                nome VARCHAR(20) NOT NULL
);

-- Tabela de acomodações
CREATE TABLE tb_acomodacoes (
                                id BIGSERIAL PRIMARY KEY,
                                titulo VARCHAR(120) NOT NULL,
                                descricao TEXT,
                                preco_diaria DECIMAL(10,2) NOT NULL,
                                endereco_completo VARCHAR(255) NOT NULL,
                                max_hospedes INTEGER NOT NULL,
                                quantidade_quartos INTEGER NOT NULL,
                                quantidade_banheiros INTEGER NOT NULL,
                                aceita_pets BOOLEAN NOT NULL,
                                tipo_acomodacao VARCHAR(20) NOT NULL
);

-- Tabela intermediária de comodidades e acomodações
CREATE TABLE comodidades_acomodacoes (
                                         acomodacao_id BIGINT NOT NULL,
                                         comodidade_id BIGINT NOT NULL,
                                         PRIMARY KEY (acomodacao_id, comodidade_id),
                                         FOREIGN KEY (acomodacao_id) REFERENCES tb_acomodacoes(id),
                                         FOREIGN KEY (comodidade_id) REFERENCES tb_comodidades(id)
);

-- Tabela de reservas
CREATE TABLE tb_reservas (
                             id BIGSERIAL PRIMARY KEY,
                             usuario_id BIGINT NOT NULL,
                             acomodacao_id BIGINT NOT NULL,
                             data_checkin DATE NOT NULL,
                             data_checkout DATE NOT NULL,
                             quantidade_diarias INTEGER NOT NULL,
                             valor_pagamento DECIMAL(10,2) NOT NULL,
                             data_reserva TIMESTAMP NOT NULL,
                             FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(id),
                             FOREIGN KEY (acomodacao_id) REFERENCES tb_acomodacoes(id)
);

-- Tabela de avaliações
CREATE TABLE tb_avaliacoes (
                               id BIGSERIAL PRIMARY KEY,
                               reserva_id BIGINT NOT NULL UNIQUE,
                               nota INTEGER NOT NULL,
                               comentario TEXT,
                               data_avaliacao TIMESTAMP NOT NULL,
                               FOREIGN KEY (reserva_id) REFERENCES tb_reservas(id)
);