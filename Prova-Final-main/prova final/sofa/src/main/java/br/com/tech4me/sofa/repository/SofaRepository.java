package br.com.tech4me.sofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.sofa.model.Sofa;

@Repository
public interface SofaRepository extends JpaRepository<Sofa, String> {

}
