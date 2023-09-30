package br.com.tech4me.servico.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.servico.shared.ServicoCompletoDTO;
import br.com.tech4me.servico.shared.ServicoDTO;

public interface ServicoService {
    List<ServicoCompletoDTO> obterServico();

    Optional<ServicoCompletoDTO> obterServicoPorId(String id);

    ServicoCompletoDTO cadastrarServico(ServicoDTO servicoDTO);

    Optional<ServicoCompletoDTO> atualiazrServicoPorId(String id, ServicoDTO servicoDTO);

    void excluirServicoPorId(String id);
}
