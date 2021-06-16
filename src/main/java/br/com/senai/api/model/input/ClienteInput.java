package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

    private long id;

    private String nome;

    private String email;

    private String telefone;

}
