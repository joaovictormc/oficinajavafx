package oficinajavafx.model.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Orcamento {

    private int id_os;
    private String tipo_veiculo;
    private String modelo_veiculo;
    private String ano_veiculo;
    private String avarias;
    private Date data_entrada;
    private Date data_saida;
    private String defeito_relatado;
    private String defeito_constatado;
    private int descontos;
    private BigDecimal valor_final;
    private boolean situacao;
    private int id_cliente;
    private int id_servicos;

    public Orcamento(int id_os, String tipo_veiculo, String modelo_veiculo, String ano_veiculo, String avarias, Date data_entrada, Date data_saida, String defeito_relatado, String defeito_constatado, int descontos, BigDecimal valor_final, boolean situacao, int id_cliente, int id_servicos) {
        this.id_os = id_os;
        this.tipo_veiculo = tipo_veiculo;
        this.modelo_veiculo = modelo_veiculo;
        this.ano_veiculo = ano_veiculo;
        this.avarias = avarias;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.defeito_relatado = defeito_relatado;
        this.defeito_constatado = defeito_constatado;
        this.descontos = descontos;
        this.valor_final = valor_final;
        this.situacao = situacao;
        this.id_cliente = id_cliente;
        this.id_servicos = id_servicos;
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

    public String getAvarias() {
        return avarias;
    }

    public void setAvarias(String avarias) {
        this.avarias = avarias;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public void setData_saida(Date data_saida) {
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

    public BigDecimal getValor_final() {
        return valor_final;
    }

    public void setValor_final(BigDecimal valor_final) {
        this.valor_final = valor_final;
    }

    public boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_servicos() {
        return id_servicos;
    }

    public void setId_servicos(int id_servicos) {
        this.id_servicos = id_servicos;
    }
}
