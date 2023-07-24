package oficinajavafx.model.domain;

import java.time.LocalDate;
import java.util.Date;


public class Agendamento {
    private int id_agenda;
    private LocalDate data_inicio_Servico;
    private LocalDate data_final_servico;
    private String tipo_pagamento = "";
    private Double valor_pago = 0.0;
    private boolean pagamento = false;
    private String nome_cliente;
    private String cpf_cliente;
    
    public Agendamento() {
      
    }

    public Agendamento(int id_agenda, 
                    LocalDate data_inicio_Servico, 
                    LocalDate data_final_servico, 
                    String tipo_pagamento, 
                    Double valor_pago, 
                    boolean pagamento,
                    String nome_cliente,
                    String cpf_cliente) {
        this.id_agenda = id_agenda;
        this.data_inicio_Servico = data_inicio_Servico;
        this.data_final_servico = data_final_servico;
        this.tipo_pagamento = tipo_pagamento;
        this.valor_pago = valor_pago;
        this.pagamento = pagamento;
        this.nome_cliente = nome_cliente;
        this.cpf_cliente = cpf_cliente;
    }

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }

    public Double getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(Double valor_pago) {
        this.valor_pago = valor_pago;
    }

    public int getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public LocalDate getData_inicio_servico() {
        return data_inicio_Servico;
    }

    public void setData_inicio_servico(LocalDate data_Inicio_Servico) {
        this.data_inicio_Servico = data_Inicio_Servico;
    }

    public LocalDate getData_final_servico() {
        return data_final_servico;
    }

    public void setData_final_servico(LocalDate data_final_servico) {
        this.data_final_servico = data_final_servico;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
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
