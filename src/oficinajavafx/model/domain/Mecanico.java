package oficinajavafx.model.domain;

import java.math.BigDecimal;

public class Mecanico {
    private int id_mec;
    private String nome_mec;
    private String telefone_mec;
    private String especialidade;
    private String email;
    private BigDecimal salario;
    private boolean disponibilidade;
    private String status;

    public Mecanico(int id_mec, String nome_mec, String telefone_mec, String especialidade, String email, BigDecimal salario, boolean disponibilidade, String status) {
        this.id_mec = id_mec;
        this.nome_mec = nome_mec;
        this.telefone_mec = telefone_mec;
        this.especialidade = especialidade;
        this.email = email;
        this.salario = salario;
        this.disponibilidade = disponibilidade;
        this.status = status;
    }

    public int getId_mec() {
        return id_mec;
    }

    public void setId_mec(int id_mec) {
        this.id_mec = id_mec;
    }

    public String getNome_mec() {
        return nome_mec;
    }

    public void setNome_mec(String nome_mec) {
        this.nome_mec = nome_mec;
    }

    public String getTelefone_mec() {
        return telefone_mec;
    }

    public void setTelefone_mec(String telefone_mec) {
        this.telefone_mec = telefone_mec;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
