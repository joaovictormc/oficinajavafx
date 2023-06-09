package oficinajavafx.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Orcamento implements Serializable{

    private int id_os;
    private String tipo_veiculo;
    private String modelo_veiculo;
    private String ano_veiculo;
    private String marca_veiculo;
    private String avarias;
    private LocalDate data_entrada;
    private LocalDate data_saida;
    private String defeito_relatado;
    private String defeito_constatado;
    private int descontos;
    private double valor_final;
    private boolean situacao;
    private List<String> tipo_servico;
    private int id_cli;
    
    public Orcamento() {
        this.tipo_servico = new ArrayList<>();
    }

    public Orcamento(int id_os, String tipo_veiculo, String modelo_veiculo, String ano_veiculo, String marca_veiculo, String avarias, LocalDate data_entrada, LocalDate data_saida, String defeito_relatado, String defeito_constatado, int descontos, double valor_final, boolean situacao, List<Servico> servico, Cliente cliente) {
        this.id_os = id_os;
        this.tipo_veiculo = tipo_veiculo;
        this.modelo_veiculo = modelo_veiculo;
        this.ano_veiculo = ano_veiculo;
        this.marca_veiculo = marca_veiculo;
        this.avarias = avarias;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.defeito_relatado = defeito_relatado;
        this.defeito_constatado = defeito_constatado;
        this.descontos = descontos;
        this.valor_final = valor_final;
        this.situacao = situacao;
        this.tipo_servico = new ArrayList<>();
        for (Servico s : servico) {
            this.tipo_servico.add(s.getTipo_Servico());
        }
        this.id_cli = cliente.getId_Cli();
    }

    public int getId_os() {
        return id_os;
    }

    public void setId_os(int id_os) {
        this.id_os = id_os;
    }

    public String getTipo_veiculo() {
        return tipo_veiculo;
    }

    public void setTipo_veiculo(String tipo_veiculo) {
        this.tipo_veiculo = tipo_veiculo;
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

    public String getAvarias() {
        return avarias;
    }

    public void setAvarias(String avarias) {
        this.avarias = avarias;
    }

    public LocalDate getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDate data_entrada) {
        this.data_entrada = data_entrada;
    }

    public LocalDate getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDate data_saida) {
        this.data_saida = data_saida;
    }

    public String getDefeito_relatado() {
        return defeito_relatado;
    }

    public void setDefeito_relatado(String defeito_relatado) {
        this.defeito_relatado = defeito_relatado;
    }

    public String getDefeito_constatado() {
        return defeito_constatado;
    }

    public void setDefeito_constatado(String defeito_constatado) {
        this.defeito_constatado = defeito_constatado;
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

    public boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public List<String> getTipo_servico() {
        return tipo_servico;
    }

    public void setTipo_servico(List<String> tipo_servico) {
        this.tipo_servico = tipo_servico;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }
    
}
