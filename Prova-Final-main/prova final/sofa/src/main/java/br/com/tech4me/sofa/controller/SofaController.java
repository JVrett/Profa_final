package br.com.tech4me.sofa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import br.com.tech4me.sofa.service.SofaService;
import br.com.tech4me.sofa.shared.SofaDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sofas")
public class SofaController {

    @Autowired
    private SofaService servico;

     @GetMapping("/porta")
    private String obterPorta(@Value("${local.server.port}") String porta){
        return "A porta dessa instância é a: " + porta;
    }



    @GetMapping
    public ResponseEntity<List<SofaDTO>> obterTodos() {
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SofaDTO> obterSofaPorId(@PathVariable String id) {
        Optional<SofaDTO> sofaDTO = servico.obterSofaPorId(id);

        if (sofaDTO.isPresent()) {
            return new ResponseEntity<>(sofaDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SofaDTO> cadastrarSofa(@RequestBody @Valid SofaDTO sofa) {
        return new ResponseEntity<>(servico.cadastrarSofa(sofa), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SofaDTO> atualizarSofaPorid(@PathVariable String id, @RequestBody @Valid SofaDTO sofa) {

        Optional<SofaDTO> sofaAtualizado = servico.atualizarSofaPorId(id, sofa);

        if (sofaAtualizado.isPresent()) {
            return new ResponseEntity<>(sofaAtualizado.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable String id) {
        servico.excluirSofaPorId(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
