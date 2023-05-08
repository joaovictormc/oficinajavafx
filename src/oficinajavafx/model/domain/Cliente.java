package oficinajavafx.model.domain;

import java.io.Serializable;

public class Cliente implements Serializable{
    private int id_cli;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private String email;
    
    public Cliente() {}

    public Cliente(int id_cli, String nome, String endereco, String telefone, String cpf, String email) {
        this.id_cli = id_cli;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
    }

    
    public int getId_Cli() {
        return id_cli;
    }

    public void setId_Cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}


    
    
