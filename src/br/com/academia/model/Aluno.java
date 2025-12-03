package br.com.academia.model;

public class Aluno {

    private int alunoID;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String dataNascimento;


    private String planoAtual;

    public int getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(int alunoID) {
        this.alunoID = alunoID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPlanoAtual() {
        return planoAtual;
    }

    public void setPlanoAtual(String planoAtual) {
        this.planoAtual = planoAtual;
    }

    @Override
    public String toString() {
        return nome;
    }
}
