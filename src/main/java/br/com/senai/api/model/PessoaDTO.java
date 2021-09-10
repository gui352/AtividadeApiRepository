package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private Long id;
    private String nome;
    private String telefone;
    private UsuarioDTO usuario;

}
