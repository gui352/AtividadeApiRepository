package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DestinatarioInput {

    @NotBlank
    private String nome;

   @NotBlank
    private String logradouro;

   @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String bairro;


}
