package oficinajavafx.model.domain;

import java.util.Date;


public class Agendamento {
    private int id_agenda;
    private Date data_Inicio_Servico;
    private Date data_final_servico;
    private boolean pagamento;
    private String observacoes;
    private int id_Mecanico;
    private int id_Cliente;
    private int id_Orcamentos;

    public Agendamento(int id_agenda, Date data_Inicio_Servico, Date data_final_servico, boolean pagamento, String observacoes, int id_Mecanico, int id_Cliente, int id_Orcamentos) {
        this.id_agenda = id_agenda;
        this.data_Inicio_Servico = data_Inicio_Servico;
        this.data_final_servico = data_final_servico;
        this.pagamento = pagamento;
        this.observacoes = observacoes;
        this.id_Mecanico = id_Mecanico;
        this.id_Cliente = id_Cliente;
        this.id_Orcamentos = id_Orcamentos;
    }

    public int getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public Date getData_inicio_servico() {
        return data_Inicio_Servico;
    }

    public void setData_inicio_servico(Date data_Inicio_Servico) {
        this.data_Inicio_Servico = data_Inicio_Servico;
    }

    public Date getData_final_servico() {
        return data_final_servico;
    }

    public void setData_final_servico(Date data_final_servico) {
        this.data_final_servico = data_final_servico;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getId_mecanico() {
        return id_Mecanico;
    }

    public void setId_mecanico(int id_Mecanico) {
        this.id_Mecanico = id_Mecanico;
    }

    public int getId_cliente() {
        return id_Cliente;
    }

    public void setId_cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_orcamentos() {
        return id_Orcamentos;
    }

    public void setId_orcamentos(int id_Orcamentos) {
        this.id_Orcamentos = id_Orcamentos;
    }
}
