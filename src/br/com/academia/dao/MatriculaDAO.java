package br.com.academia.dao;

import br.com.academia.db.ConnectionFactory;
import br.com.academia.model.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public void inserir(Matricula m) {
        String sql = "INSERT INTO Matricula (AlunoID, PlanoID, DataInicio, Status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getAlunoID());
            stmt.setInt(2, m.getPlanoID());
            stmt.setDate(3, java.sql.Date.valueOf(m.getDataInicio()));
            stmt.setString(4, m.getStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir matrícula: " + e.getMessage());
        }
    }

    public List<Matricula> listar() {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM Matricula";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Matricula m = new Matricula();
                m.setMatriculaID(rs.getInt("MatriculaID"));
                m.setAlunoID(rs.getInt("AlunoID"));
                m.setPlanoID(rs.getInt("PlanoID"));
                m.setDataInicio(rs.getDate("DataInicio").toLocalDate());
                m.setStatus(rs.getString("Status"));

                lista.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar matrículas: " + e.getMessage());
        }

        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Matricula WHERE MatriculaID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir matrícula: " + e.getMessage());
        }
    }

    public void atualizarStatus(int id, String status) {
        String sql = "UPDATE Matricula SET Status = ? WHERE MatriculaID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status: " + e.getMessage());
        }
    }


}
