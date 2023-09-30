package br.com.tech4me.sofa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.sofa.model.Sofa;
import br.com.tech4me.sofa.repository.SofaRepository;
import br.com.tech4me.sofa.shared.SofaDTO;

@Service
public class SofaServiceIMP implements SofaService {

    @Autowired
    private SofaRepository repositorio;

    @Override
    public List<SofaDTO> obterTodos() {
        return repositorio.findAll()
                .stream()
                .map(s -> SofaDTO.from(s))
                .toList();

    }

    @Override
    public Optional<SofaDTO> obterSofaPorId(String id) {
        Optional<Sofa> sofa = repositorio.findById(id);

        if (sofa.isPresent()) {
            return Optional.of(new SofaDTO(sofa.get().getId(), sofa.get().getTamanho(), sofa.get().getTecido(),
                    sofa.get().getCor()));
        }
        return Optional.empty();
    }

    @Override
    public SofaDTO cadastrarSofa(SofaDTO sofaDTO) {
        Sofa sofa = new Sofa(sofaDTO);
        repositorio.save(sofa);

        return SofaDTO.from(sofa);
    }

    @Override
    public Optional<SofaDTO> atualizarSofaPorId(String id, SofaDTO sofaDTO) {
        Optional<Sofa> atualizarSofa = repositorio.findById(id);

        if (atualizarSofa.isPresent()) {
            Sofa atualizador = Sofa.fromSofaDTO(sofaDTO);
            atualizador.setId(id);
            repositorio.save(atualizador);

            return Optional.of(SofaDTO.from(atualizador));
        }

        return Optional.empty();
    }

    @Override
    public void excluirSofaPorId(String id) {
        repositorio.deleteById(id);

    }
}
