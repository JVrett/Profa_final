package br.com.tech4me.servico.shared;

import java.time.LocalDate;

import br.com.tech4me.servico.model.Servico;
import br.com.tech4me.servico.model.Sofa;
import br.com.tech4me.servico.model.Tipo;

public record ServicoCompletoDTO(String id,
                Tipo tipo,
                LocalDate data_servico,
                String endereco,
                Double valor,
                Sofa Sofa) {

        public static ServicoCompletoDTO fromCompleto(Servico servico) {
                return new ServicoCompletoDTO(servico.getId(),
                                servico.getTipo(),
                                servico.getData_servico(),
                                servico.getEndereco(),
                                servico.getValor(),
                                servico.getSofa());
        }

}
