package br.com.tech4me.servico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.servico.service.ServicoService;
import br.com.tech4me.servico.shared.ServicoCompletoDTO;
import br.com.tech4me.servico.shared.ServicoDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Servicos")
public class ServicoController {
    @Autowired
    private ServicoService servico;

    @GetMapping
    public ResponseEntity<List<ServicoCompletoDTO>> obterTodos() {
        return new ResponseEntity<>(servico.obterServico(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoCompletoDTO> obterServicoPorId(@PathVariable String id) {
        Optional<ServicoCompletoDTO> servicoDto = servico.obterServicoPorId(id);

        if (servicoDto.isPresent()) {
            return new ResponseEntity<>(servicoDto.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ServicoCompletoDTO> cadastrarSofa(@RequestBody @Valid ServicoDTO servicoDTO) {
        return new ResponseEntity<>(servico.cadastrarServico(servicoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoCompletoDTO> atualizarServicoPorId(@PathVariable String id,
            @RequestBody @Valid ServicoDTO servicoDTO) {
        Optional<ServicoCompletoDTO> servicoAtualizado = servico.atualiazrServicoPorId(id, servicoDTO);

        if (servicoAtualizado.isPresent()) {
            return new ResponseEntity<>(servicoAtualizado.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServicoPorId(@PathVariable String id) {
        servico.excluirServicoPorId(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
