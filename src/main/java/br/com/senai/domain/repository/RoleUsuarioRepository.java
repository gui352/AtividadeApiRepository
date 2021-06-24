package br.com.senai.domain.repository;

import br.com.senai.domain.model.RoleUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUsuarioRepository extends JpaRepository<RoleUsuario, Long> {

}
