INSERT INTO cartoes (numero_cartao, nome_titular, cpf_titular, senha, data_emissao, status) VALUES
  ('1111222233334444', 'Ana Silva',      '12345678901', '123456', TIMESTAMP '2025-06-28 10:00:00', 'ATIVO'),
  ('5555666677778888', 'Bruno Costa',    '23456789012', '654321', TIMESTAMP '2025-06-28 11:30:00', 'ATIVO'),
  ('9999888877776666', 'Carla Dias',     '34567890123', '111111', TIMESTAMP '2025-06-28 14:00:00', 'ATIVO'),
  ('1234567890123456', 'Daniel Souza',   '45678901234', '222222', TIMESTAMP '2025-06-27 09:00:00', 'BLOQUEADO'),
  ('4321654321098765', 'Elisa Ferreira', '56789012345', '333333', TIMESTAMP '2025-06-29 08:15:00', 'ATIVO');

INSERT INTO saldos_cartao (cartao_id, moeda, saldo) VALUES
  (1, 'USD', 1500.00),
  (1, 'EUR', 800.50),
  (2, 'USD', 250.75),
  (4, 'JPY', 50000),
  (5, 'GBP', 120.00);

INSERT INTO transacoes (cartao_id, tipo, moeda, valor_real_brasileiro, valor_convertido_moeda_destino, taxa_conversao, status, data_transacao, descricao) VALUES
  (1, 'CARGA', 'USD', 5250.00, 1000.00, 5.25, 'APROVADA', TIMESTAMP '2025-06-28 10:05:00', 'Carga de 1000 USD'),
  (2, 'CARGA', 'USD', 1316.44,  250.75, 5.25, 'APROVADA', TIMESTAMP '2025-06-28 11:32:00', 'Carga de 250.75 USD'),
  (1, 'SAQUE', 'USD', 2625.00,  500.00, 5.25, 'APROVADA', TIMESTAMP '2025-06-29 09:45:00', 'Saque de 500 USD'),
  (1, 'CARGA', 'EUR', 4562.85,  800.50, 5.70, 'APROVADA', TIMESTAMP '2025-06-28 10:10:00', 'Carga de 800.50 EUR'),
  (5, 'CARGA', 'GBP',  792.00,  120.00, 6.60, 'APROVADA', TIMESTAMP '2025-06-29 08:20:00', 'Carga de 120 GBP');
