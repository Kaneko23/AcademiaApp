package br.com.academia.dao;

import br.com.academia.db.ConnectionFactory;
import br.com.academia.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {


    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO Aluno (Nome, CPF, Email, Telefone, DataNascimento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getDataNascimento());

            stmt.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir aluno: " + e.getMessage());
        }
    }


    public List<Aluno> listarComPlano() {
        List<Aluno> lista = new ArrayList<>();

        String sql = """
            SELECT a.*, p.NomePlano
            FROM Aluno a
            LEFT JOIN Matricula m ON m.AlunoID = a.AlunoID AND m.Status = 'Ativa'
            LEFT JOIN Plano p ON p.PlanoID = m.PlanoID
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Aluno a = new Aluno();

                a.setAlunoID(rs.getInt("AlunoID"));
                a.setNome(rs.getString("Nome"));
                a.setCpf(rs.getString("CPF"));
                a.setEmail(rs.getString("Email"));
                a.setTelefone(rs.getString("Telefone"));
                a.setDataNascimento(rs.getString("DataNascimento"));
                a.setPlanoAtual(rs.getString("NomePlano"));

                lista.add(a);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage());
        }

        return lista;
    }


    public void atualizar(Aluno aluno) {
        String sql = "UPDATE Aluno SET Nome=?, CPF=?, Email=?, Telefone=?, DataNascimento=? WHERE AlunoID=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getDataNascimento());
            stmt.setInt(6, aluno.getAlunoID());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage());
        }
    }


    public void excluir(int alunoID) {
        String sql = "DELETE FROM Aluno WHERE AlunoID=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoID);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage());
        }
    }


    public List<Aluno> buscar(String termo) {
        List<Aluno> lista = new ArrayList<>();

        String sql;

        // 🔎 Buscando por número? Então é ID
        if (termo.matches("\\d+")) {
            sql = """
                SELECT a.*, p.NomePlano
                FROM Aluno a
                LEFT JOIN Matricula m ON m.AlunoID = a.AlunoID AND m.Status='Ativa'
                LEFT JOIN Plano p ON p.PlanoID = m.PlanoID
                WHERE a.AlunoID = ?
            """;
        }
        // 🔎 CPF
        else if (termo.matches("\\d{11}")) {
            sql = """
                SELECT a.*, p.NomePlano
                FROM Aluno a
                LEFT JOIN Matricula m ON m.AlunoID = a.AlunoID AND m.Status='Ativa'
                LEFT JOIN Plano p ON p.PlanoID = m.PlanoID
                WHERE a.CPF LIKE ?
            """;
            termo = "%" + termo + "%";
        }
        // 🔎 Nome
        else {
            sql = """
                SELECT a.*, p.NomePlano
                FROM Aluno a
                LEFT JOIN Matricula m ON m.AlunoID = a.AlunoID AND m.Status='Ativa'
                LEFT JOIN Plano p ON p.PlanoID = m.PlanoID
                WHERE a.Nome LIKE ?
            """;
            termo = "%" + termo + "%";
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 🎯 Decide ID ou String
            if (sql.contains("AlunoID = ?")) {
                stmt.setInt(1, Integer.parseInt(termo));
            } else {
                stmt.setString(1, termo);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setAlunoID(rs.getInt("AlunoID"));
                a.setNome(rs.getString("Nome"));
                a.setCpf(rs.getString("CPF"));
                a.setEmail(rs.getString("Email"));
                a.setTelefone(rs.getString("Telefone"));
                a.setDataNascimento(rs.getString("DataNascimento"));
                a.setPlanoAtual(rs.getString("NomePlano"));
                lista.add(a);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno", e);
        }

        return lista;
    }


    public boolean cpfExiste(String cpf) {
        String sql = "SELECT CPF FROM Aluno WHERE CPF = ? LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar CPF: " + e.getMessage());
        }
    }
}
