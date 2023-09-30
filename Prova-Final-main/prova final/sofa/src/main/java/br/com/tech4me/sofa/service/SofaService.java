package br.com.tech4me.sofa.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.sofa.shared.SofaDTO;

public interface SofaService {
    List<SofaDTO> obterTodos();

    Optional<SofaDTO> obterSofaPorId(String id);

    SofaDTO cadastrarSofa(SofaDTO sofaDTO);

    Optional<SofaDTO> atualizarSofaPorId(String id, SofaDTO sofaDTO);

    void excluirSofaPorId(String id);
}
