CREATE TABLE rule_usuarios (
    usuarios_id bigint not null ,
    rule_nome_rule varchar(45) not null
);

ALTER TABLE rule_usuarios ADD CONSTRAINT fk_usuarios
FOREIGN KEY (usuarios_id) REFERENCES usuario (id);

ALTER TABLE rule_usuarios ADD CONSTRAINT fk_rule
FOREIGN KEY (rule_nome_rule) REFERENCES rule (nome_rule);