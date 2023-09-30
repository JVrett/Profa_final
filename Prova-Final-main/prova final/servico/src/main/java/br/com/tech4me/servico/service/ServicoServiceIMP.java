package br.com.tech4me.servico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.servico.HttpClient.SofaClient;
import br.com.tech4me.servico.Repository.ServicoRepository;
import br.com.tech4me.servico.model.Servico;
import br.com.tech4me.servico.model.Sofa;
import br.com.tech4me.servico.shared.ServicoCompletoDTO;
import br.com.tech4me.servico.shared.ServicoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ServicoServiceIMP implements ServicoService {

    @Autowired
    private ServicoRepository repositorio;

    @Autowired
    private SofaClient sofaClient;

    @CircuitBreaker(name = "obterSofa", fallbackMethod = "fallbackObterServico")
    @Override
    public List<ServicoCompletoDTO> obterServico() {
        return repositorio.findAll()
                .stream()
                .map(s -> ServicoCompletoDTO.fromCompleto(s))
                .toList();
    }

    @CircuitBreaker(name = "obterSofaPorId", fallbackMethod = "fallbackServicoPorId")
    @Override
    public Optional<ServicoCompletoDTO> obterServicoPorId(String id) {
        Optional<Servico> servico = repositorio.findById(id);

        if (servico.isPresent()) {
            
           return Optional.of(new ServicoCompletoDTO(servico.get().getId(), servico.get().getTipo(),
                    servico.get().getData_servico(),
                    servico.get().getEndereco(), servico.get().getValor(), servico.get().getSofa()));
        }
        return Optional.empty();
    }

    @CircuitBreaker(name = "obterServicoComSofa", fallbackMethod = "fallbackCadastrarServico")
    @Override
    public ServicoCompletoDTO cadastrarServico(ServicoDTO servicoDTO) {
        
        Sofa sofa = Sofa.fromSofaDTO(sofaClient.obterSofa(servicoDTO.sofa_id()));

        Servico servico = Servico.fromServico(servicoDTO, sofa);
        repositorio.save(servico);

        return ServicoCompletoDTO.fromCompleto(servico);

    }

    @CircuitBreaker(name = "atualizarServicoComSofa", fallbackMethod = "fallbeckAtualizarPorId")
    @Override
    public Optional<ServicoCompletoDTO> atualiazrServicoPorId(String id, ServicoDTO servicoDTO) {
        Optional<Servico> servicoAtualizado = repositorio.findById(id);

        if (servicoAtualizado.isPresent()) {
             Sofa sofa = Sofa.fromSofaDTO(sofaClient.obterSofa(servicoDTO.sofa_id()));
            Servico atualizador =Servico.fromServico(servicoDTO,sofa);
            atualizador.setId(id);
            return Optional.of(ServicoCompletoDTO.fromCompleto(atualizador));

        }
        return Optional.empty();
    }

    @Override
    public void excluirServicoPorId(String id) {
        repositorio.deleteById(id);
    }

    // Metodos fallback

    public List<ServicoCompletoDTO> fallbackObterServico(Exception e) {
        return repositorio.findAll()
                .stream()
                .map(s -> new ServicoCompletoDTO(s.getId(), s.getTipo(), s.getData_servico(), s.getEndereco(),
                        s.getValor(), null))
                .toList();

    }

    public Optional<ServicoCompletoDTO> fallbackServicoPorId(String id, Exception e) {
        Optional<Servico> servico = repositorio.findById(id);

        if (servico.isPresent()) {
            ServicoCompletoDTO servicoDoSofa = new ServicoCompletoDTO(servico.get().getId(), servico.get().getTipo(),
                    servico.get().getData_servico(), servico.get().getEndereco(), servico.get().getValor(), null);

            return Optional.of(servicoDoSofa);
        } else {
            return Optional.empty();
        }
    }

    public ServicoCompletoDTO fallbackCadastrarServico(ServicoDTO servicoDTO, Exception e) {

        Servico servico = Servico.fromServico(servicoDTO, null);
        repositorio.save(servico);

        return ServicoCompletoDTO.fromCompleto(servico);

    }

    public Optional<ServicoCompletoDTO> fallbeckAtualizarPorId(String id, ServicoCompletoDTO servicoDTO, Exception e) {
        Optional<Servico> servico = repositorio.findById(id);

        if (servico.isPresent()) {
            Servico atualizador = new Servico(servicoDTO);
            atualizador.setId(id);
            return Optional.of(
                    new ServicoCompletoDTO(atualizador.getId(), atualizador.getTipo(), atualizador.getData_servico(),
                            atualizador.getEndereco(), atualizador.getValor(), null));
        }
        return Optional.empty();
    }

}
