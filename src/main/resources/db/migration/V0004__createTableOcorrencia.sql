CREATE TABLE ocorrencia (
    id bigint not null auto_increment,
    entrega_id bigint not null,
    descricao text not null,
    data_registro timestamp not null,
    primary key (id)
);

alter table ocorrencia add constraint fk_ocorrencia_entrega
foreign key (entrega_id) references entregas(id);