package br.com.tech4me.servico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.servico.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, String> {

}
