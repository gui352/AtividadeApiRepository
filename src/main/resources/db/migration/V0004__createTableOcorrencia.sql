CREATE TABLE ocorrencia(
    id bigint NOT NULL auto_increment,
    entrega_id bigint NOT NULL,
    descricao text NOT NULL,
    data_registro TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE ocorrencia ADD CONSTRAINT fk_ocorrencia_entrega
FOREIGN KEY (entrega_id) REFERENCES entrega (id);