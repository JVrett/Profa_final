package br.com.tech4me.servico.shared;

import br.com.tech4me.servico.model.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record ServicoDTO(String id,
                @NotNull(message = "Od tipos de servicos disponiceis são : LAVAGEM_PROFUNDA, LAVAGEM-SIMPLES e IMPEMIABILIZAÇÃO ") Tipo tipo,
                @NotEmpty(message = "o campo da data do servico deve ser informado. por favor digite novamente!") @NotBlank(message = "o campo da data do servico esta com caracteres vazios. por favor digite novamente!") String data_servico,
                @NotEmpty(message = "o campo do endereço deve ser informado. por favor digite novamente!") @NotBlank(message = "o campo do endereço esta com caracteres vazios. por favor digite novamente!") String endereco,
                 @NotNull(message = "o campo do valor nao pode ser nulo. Por favor digite novamente")Double valor,
                @NotEmpty(message = "o campo do id do Sofa deve ser informado. por favor digite novamente!") @NotBlank(message = "o campo do Id do Sofa esta com caracteres vazios. por favor digite novamente!") String sofa_id) {

    

}
