package oficinajavafx.model.domain;

import java.math.BigDecimal;

public class Servico {

    private int id_servicos;
    private String tipo_Servico;
    private String tempo_Estimado;
    private BigDecimal valor;
    private int id_mecanico;
    private String complexidade;
    
    
    public Servico(int id_servicos, String tipo_Servico, String tempo_Estimado, BigDecimal valor, int id_mecanico, String complexidade) {
        this.id_servicos = id_servicos;
        this.tipo_Servico = tipo_Servico;
        this.tempo_Estimado = tempo_Estimado;
        this.valor = valor;
        this.id_mecanico = id_mecanico;
        this.complexidade = complexidade;
    }
    

    // Getters e Setters
    public int getId_servicos() {
        return id_servicos;
    }

    public void setId_serveicos(int id_servicos) {
        this.id_servicos = id_servicos;
    }

    public String getTipo_servico() {
        return tipo_Servico;
    }

    public void setTipo_servico(String tipo_Servico) {
        this.tipo_Servico = tipo_Servico;
    }

    public String getTempo_estimado() {
        return tempo_Estimado;
    }

    public void setTempo_estimado(String tempo_Estimado) {
        this.tempo_Estimado = tempo_Estimado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(int id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(String complexidade) {
        this.complexidade = complexidade;
    }

    // toString
    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id_servicos +
                ", tipoServico='" + tipo_Servico + '\'' +
                ", tempoEstimado='" + tempo_Estimado + '\'' +
                ", valor=" + valor +
                ", mecanico=" + id_mecanico +
                ", complexidade='" + complexidade + '\'' +
                '}';
    }
}

