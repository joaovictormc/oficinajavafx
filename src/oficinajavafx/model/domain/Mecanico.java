package oficinajavafx.model.domain;


public class Mecanico {
    private int id_mec;
    private String nome_mec;
    private String telefone_mec;
    private String especialidade;
    private String email;
    private double salario;
    private boolean disponibilidade = true;
    private int servicosSendoFeitos;

    public Mecanico() {
    }

    public Mecanico(int id_mec, String nome_mec, String telefone_mec, String especialidade, String email, double salario, boolean disponibilidade) {
        this.id_mec = id_mec;
        this.nome_mec = nome_mec;
        this.telefone_mec = telefone_mec;
        this.especialidade = especialidade;
        this.email = email;
        this.salario = salario;
        this.disponibilidade = disponibilidade;
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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    
    public int getServicosSendoFeitos() {
        return servicosSendoFeitos;
    }
    
    public void setServicosSendoFeitos(int servicosSendoFeitos) {
        this.servicosSendoFeitos = servicosSendoFeitos;
    }

    public void addServicosSendoFeitos(int num) {
        this.servicosSendoFeitos =+ num;
        if (this.servicosSendoFeitos == 2) {
            this.disponibilidade = false;
        }
    }
    
    public void removerServicosSendoFeitos(int num) {
        this.servicosSendoFeitos =- num;
        this.disponibilidade = true;
    }

    @Override
    public String toString() {
        return this.nome_mec;
    }
    
}
