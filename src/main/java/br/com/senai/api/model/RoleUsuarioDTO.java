package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUsuarioDTO {

    private Long id;

    private Long usuarios_id;

    private  String role_nome_role;

}
