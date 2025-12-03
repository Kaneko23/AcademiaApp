package br.com.academia.model;

import java.time.LocalDate;

public class Matricula {

    private int MatriculaID;
    private int AlunoID;
    private int PlanoID;
    private LocalDate DataInicio;
    private String Status;

    public int getMatriculaID() { return MatriculaID; }
    public void setMatriculaID(int id) { this.MatriculaID = id; }

    public int getAlunoID() { return AlunoID; }
    public void setAlunoID(int id) { this.AlunoID = id; }

    public int getPlanoID() { return PlanoID; }
    public void setPlanoID(int id) { this.PlanoID = id; }

    public LocalDate getDataInicio() { return DataInicio; }
    public void setDataInicio(LocalDate data) { this.DataInicio = data; }

    public String getStatus() { return Status; }
    public void setStatus(String s) { this.Status = s; }
}
