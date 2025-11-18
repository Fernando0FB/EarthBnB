-- Inserir usuários
-- Todas as senhas inseridas são 123123
INSERT INTO tb_usuarios (nome, email, usuario, senha, telefone, criado_em) VALUES
                                                                               ('João Silva', 'joao@email.com', 'joao', '$2y$10$62em7V7NGV90D3ljF7i5M.k/zaz0mKAcRWs6bfp05BQs2nLQza8ge', '47999887766', CURRENT_DATE),
                                                                               ('Maria Santos', 'maria@email.com', 'maria', '$2y$10$62em7V7NGV90D3ljF7i5M.k/zaz0mKAcRWs6bfp05BQs2nLQza8ge', '47988776655', CURRENT_DATE),
                                                                               ('Admin Sistema', 'admin@earthbnb.com', 'admin', '$2y$10$62em7V7NGV90D3ljF7i5M.k/zaz0mKAcRWs6bfp05BQs2nLQza8ge', '47977665544', CURRENT_DATE);

-- Inserir roles dos usuários
INSERT INTO user_roles (user_id, role) VALUES
                                           (1, 'ROLE_USER'),
                                           (2, 'ROLE_USER'),
                                           (3, 'ROLE_ADMIN');

-- Inserir comodidades
INSERT INTO tb_comodidades (nome) VALUES
                                      ('Wi-Fi'),
                                      ('Ar Condicionado'),
                                      ('Piscina'),
                                      ('Estacionamento'),
                                      ('TV'),
                                      ('Cozinha'),
                                      ('Máquina de Lavar'),
                                      ('Academia'),
                                      ('Churrasqueira'),
                                      ('Varanda');

-- Inserir acomodações
INSERT INTO tb_acomodacoes (titulo, descricao, preco_diaria, endereco_completo, max_hospedes, quantidade_quartos, quantidade_banheiros, aceita_pets, tipo_acomodacao) VALUES
                                                                                                                                                                          ('Apartamento Aconchegante Centro', 'Lindo apartamento no coração da cidade com vista panorâmica', 250.00, 'Rua XV de Novembro, 1234 - Centro, Criciúma - SC', 4, 2, 1, true, 'APARTAMENTO'),
                                                                                                                                                                          ('Casa de Praia Confortável', 'Casa ampla a 100m da praia com churrasqueira e área gourmet', 450.00, 'Av. Atlântica, 567 - Balneário Rincão, SC', 8, 4, 3, true, 'CASA'),
                                                                                                                                                                          ('Loft Moderno', 'Loft moderno e minimalista ideal para casais', 180.00, 'Rua do Comércio, 890 - Centro, Criciúma - SC', 2, 1, 1, false, 'CASA'),
                                                                                                                                                                          ('Chalé na Serra', 'Chalé aconchegante com lareira em meio à natureza', 320.00, 'Estrada Geral, km 15 - Serra do Rio do Rastro, SC', 6, 3, 2, true, 'QUARTO'),
                                                                                                                                                                          ('Studio Compacto', 'Studio prático próximo à universidade', 120.00, 'Rua Universitária, 200 - Universitário, Criciúma - SC', 2, 1, 1, false, 'QUARTO');

-- Vincular comodidades às acomodações
-- Apartamento Centro (1) - Wi-Fi, Ar, TV, Cozinha, Varanda
INSERT INTO comodidades_acomodacoes (acomodacao_id, comodidade_id) VALUES
                                                                       (1, 1), (1, 2), (1, 5), (1, 6), (1, 10);

-- Casa Praia (2) - Wi-Fi, Ar, Piscina, Estacionamento, TV, Cozinha, Churrasqueira
INSERT INTO comodidades_acomodacoes (acomodacao_id, comodidade_id) VALUES
                                                                       (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 9);

-- Loft (3) - Wi-Fi, Ar, TV, Cozinha
INSERT INTO comodidades_acomodacoes (acomodacao_id, comodidade_id) VALUES
                                                                       (3, 1), (3, 2), (3, 5), (3, 6);

-- Chalé (4) - Wi-Fi, Cozinha, Churrasqueira, Varanda
INSERT INTO comodidades_acomodacoes (acomodacao_id, comodidade_id) VALUES
                                                                       (4, 1), (4, 6), (4, 9), (4, 10);

-- Studio (5) - Wi-Fi, TV, Cozinha
INSERT INTO comodidades_acomodacoes (acomodacao_id, comodidade_id) VALUES
                                                                       (5, 1), (5, 5), (5, 6);

-- Inserir reservas
INSERT INTO tb_reservas (usuario_id, acomodacao_id, data_checkin, data_checkout, quantidade_diarias, valor_pagamento, data_reserva) VALUES
                                                                                                                                        (1, 1, '2025-02-15', '2025-02-18', 3, 750.00, '2025-01-20 14:30:00'),
                                                                                                                                        (2, 2, '2025-03-01', '2025-03-08', 7, 3150.00, '2025-01-25 10:15:00'),
                                                                                                                                        (1, 3, '2025-01-10', '2025-01-12', 2, 360.00, '2025-01-05 16:45:00');

-- Inserir avaliações
INSERT INTO tb_avaliacoes (reserva_id, nota, comentario, data_avaliacao) VALUES
    (3, 5, 'Excelente acomodação! Muito limpo e bem localizado. Voltarei com certeza!', '2025-01-13 11:00:00');