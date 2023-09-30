package br.com.tech4me.sofa.model;

import br.com.tech4me.sofa.shared.SofaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "tech4lavagem", name = "sofa")
public class Sofa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tamanho;
    private String tecido;
    private String cor;

    public Sofa(SofaDTO sofaDTO) {
        setId(sofaDTO.id());
        setTamanho(sofaDTO.tamanho());
        setTecido(sofaDTO.tecido());
        setCor(sofaDTO.cor());
    }

    public Sofa() {

    }

    public static Sofa fromSofaDTO(SofaDTO sofaDTO) {
        Sofa sofa = new Sofa();
        sofa.setId(sofaDTO.id());
        sofa.setTamanho(sofaDTO.tamanho());
        sofa.setTecido(sofaDTO.tecido());
        sofa.setCor(sofaDTO.cor());
        return sofa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTecido() {
        return tecido;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
