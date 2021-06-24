package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioInputDOT {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

}
