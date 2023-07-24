package oficinajavafx.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Orcamento implements Serializable{

    private int id_os;
    private String modelo_veiculo;
    private String ano_veiculo;
    private String marca_veiculo;
    private LocalDate data_entrada;
    private String defeito_relatado;
    private int descontos;
    private double valor_final;
    private boolean or_finalizado = false;
    private String servico;
    private String nome_cliente;
    private String cpf_cliente;
    
    public Orcamento() {
    }

    public Orcamento(int id_os, 
                     String modelo_veiculo, 
                     String ano_veiculo, 
                     String marca_veiculo, 
                     String avarias, 
                     LocalDate data_entrada, 
                     String defeito_relatado,
                     int descontos, 
                     double valor_final,
                     boolean or_finalizado,
                     String servico,
                     String nome_cliente,
                     String cpf_cliente) {
        this.id_os = id_os;
        this.modelo_veiculo = modelo_veiculo;
        this.ano_veiculo = ano_veiculo;
        this.marca_veiculo = marca_veiculo;
        this.data_entrada = data_entrada;
        this.defeito_relatado = defeito_relatado;
        this.descontos = descontos;
        this.valor_final = valor_final;
        this.or_finalizado = or_finalizado;
        this.servico = servico;
        this.nome_cliente = nome_cliente;
        this.cpf_cliente = cpf_cliente;
    }

    public int getId_os() {
        return id_os;
    }

    public void setId_os(int id_os) {
        this.id_os = id_os;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getAno_veiculo() {
        return ano_veiculo;
    }

    public void setAno_veiculo(String ano_veiculo) {
        this.ano_veiculo = ano_veiculo;
    }

    public String getMarca_veiculo() {
        return marca_veiculo;
    }

    public void setMarca_veiculo(String marca_veiculo) {
        this.marca_veiculo = marca_veiculo;
    }

    public LocalDate getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDate data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getDefeito_relatado() {
        return defeito_relatado;
    }

    public void setDefeito_relatado(String defeito_relatado) {
        this.defeito_relatado = defeito_relatado;
    }

    public int getDescontos() {
        return descontos;
    }

    public void setDescontos(int descontos) {
        this.descontos = descontos;
    }

    public double getValor_final() {
        return valor_final;
    }

    public void setValor_final(double valor_final) {
        this.valor_final = valor_final;
    }

    public boolean getOrfinalizado() {
        return or_finalizado;
    }

    public void setOrfinalizado(boolean or_finalizado) {
        this.or_finalizado = or_finalizado;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }
    
    
    
}
