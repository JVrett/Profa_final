package br.com.tech4me.sofa.shared;

import br.com.tech4me.sofa.model.Sofa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record SofaDTO(String id,
        @NotEmpty(message = "o campo do tamanho deve ser informado. por favor digite novamente!") @NotBlank(message = "o campo do tamanho esta com caracteres vazios. por favor digite novamente!") String tamanho,
        @NotEmpty(message = "o campo do tecido deve ser informado. por favor digite novamente!") @NotBlank(message = "o campo do tecido esta com caracteres vazios. por favor digite novamente!") String tecido,
        @NotEmpty(message = "o campo da cor deve ser informado. por favor digite novamente!") @NotBlank(message = "o campo da cor esta com caracteres vazios. por favor digite novamente!") String cor) {

    public static SofaDTO from(Sofa sofa) {
        return new SofaDTO(sofa.getId(), sofa.getTamanho(), sofa.getTecido(), sofa.getCor());
    }

}
