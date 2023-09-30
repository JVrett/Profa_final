package br.com.tech4me.servico.shared;

import br.com.tech4me.servico.model.Sofa;

public record SofaDTO(String id, String tamanho, String tecido, String cor) {

    public static SofaDTO from(Sofa sofa) {
        return new SofaDTO(sofa.getId(), sofa.getTamanho(), sofa.getTecido(), sofa.getCor());
    }

}
