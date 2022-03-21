INSERT INTO tb_funcionario (nome, matricula) values ('Maria Silva', 23);
INSERT INTO tb_funcionario (nome, matricula) values ('Joao Ribeiro', 43);

INSERT INTO tb_carro (id, data_fabricacao, marca, modelo, status) VALUES (null, '2022-04-09', 'hb20', 'Hyundai', 'DISPONIVEL');
INSERT INTO tb_carro (id, data_fabricacao, marca, modelo, status) VALUES (null, '2022-11-09', 'Gol g5', 'Volkswagen', 'DISPONIVEL');
INSERT INTO tb_carro (id, data_fabricacao, marca, modelo, status) VALUES (null, '2022-07-28', 'Onix', 'Chevrolet', 'INDISPONIVEL');

INSERT INTO tb_user (id, username, password) values (null, 'admin', '$2a$12$ptlA4c5d9jmRJllCVoKTfOvisnLYmXA/3W6Ps/ffv.toAaZ6hkyPC');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);