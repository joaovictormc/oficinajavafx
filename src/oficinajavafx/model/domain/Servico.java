package oficinajavafx.model.domain;

import java.io.Serializable;

public class Servico implements Serializable{

    private int id_servicos;
    private String tipo_Servico;
    private String tempo_Estimado;
    private double valor;
    private Mecanico mecanico;
    private String complexidade;
    
    
    public Servico() {
    }

    public Servico(int id_servicos, String tipo_Servico, String tempo_Estimado, double valor, Mecanico mecanico, String complexidade) {
        this.id_servicos = id_servicos;
        this.tipo_Servico = tipo_Servico;
        this.tempo_Estimado = tempo_Estimado;
        this.valor = valor;
        this.mecanico = mecanico;
        this.complexidade = complexidade;
    }

    public int getId_servicos() {
        return id_servicos;
    }

    public void setId_servicos(int id_servicos) {
        this.id_servicos = id_servicos;
    }

    public String getTipo_Servico() {
        return tipo_Servico;
    }

    public void setTipo_Servico(String tipo_Servico) {
        this.tipo_Servico = tipo_Servico;
    }

    public String getTempo_Estimado() {
        return tempo_Estimado;
    }

    public void setTempo_Estimado(String tempo_Estimado) {
        this.tempo_Estimado = tempo_Estimado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(String complexidade) {
        this.complexidade = complexidade;
    }
    
}