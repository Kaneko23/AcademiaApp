package br.com.academia.model;

public class Plano {

    private int planoID;
    private String nomePlano;
    private double valor;
    private String descricao;
    private int duracaoMeses;

    public int getPlanoID() {
        return planoID;
    }

    public void setPlanoID(int planoID) {
        this.planoID = planoID;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    @Override
    public String toString() {
        return nomePlano;
    }
}
