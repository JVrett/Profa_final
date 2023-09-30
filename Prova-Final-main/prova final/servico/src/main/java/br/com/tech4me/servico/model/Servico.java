package br.com.tech4me.servico.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.tech4me.servico.shared.ServicoCompletoDTO;
import br.com.tech4me.servico.shared.ServicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "tech4lavagem", name = "servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Tipo tipo;
    private LocalDate data_servico;
    private String endereco;
    private Double valor;
    @JoinColumn(name = "sofa_id")
    @OneToOne
    private Sofa sofa;

    public  Servico(ServicoCompletoDTO servicoDTO) {
        setId(servicoDTO.id());
        setTipo(servicoDTO.tipo());
        setData_servico(servicoDTO.data_servico());
        setEndereco(servicoDTO.endereco());
        setValor(servicoDTO.valor());

    }

    public Servico() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData_servico() {
        return data_servico;
    }

    public void setData_servico(LocalDate data_servico) {
        this.data_servico = data_servico;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Sofa getSofa() {
        return sofa;
    }

    public void setSofa(Sofa sofa) {
        this.sofa = sofa;
    }

    public static Servico fromServico(ServicoDTO servicoDto, Sofa sofa) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Servico servico = new Servico();

        servico.setTipo(servicoDto.tipo());
        servico.setValor(servicoDto.valor());
        servico.setData_servico(LocalDate.parse(servicoDto.data_servico(), dtf));
        servico.setEndereco(servicoDto.endereco());
        servico.setValor(servicoDto.valor());
        servico.setSofa(sofa);
        return servico;
    }


}
