package br.com.senai.domain.repository;

import br.com.senai.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregaRopository extends JpaRepository<Entrega, Long> {

}
