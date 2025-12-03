package br.com.academia.dao;

import br.com.academia.db.ConnectionFactory;
import br.com.academia.model.Plano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public void inserir(Plano plano) {
        String sql = "INSERT INTO Plano (NomePlano, Preco, DuracaoMeses, Descricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plano.getNomePlano());
            stmt.setDouble(2, plano.getValor());
            stmt.setInt(3, plano.getDuracaoMeses());
            stmt.setString(4, plano.getDescricao());
            stmt.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir plano: " + e.getMessage());
        }
    }

    public List<Plano> listar() {
        List<Plano> lista = new ArrayList<>();
        String sql = "SELECT * FROM Plano ORDER BY PlanoID DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Plano p = new Plano();
                p.setPlanoID(rs.getInt("PlanoID"));
                p.setNomePlano(rs.getString("NomePlano"));
                p.setValor(rs.getDouble("Preco"));
                p.setDuracaoMeses(rs.getInt("DuracaoMeses"));
                p.setDescricao(rs.getString("Descricao"));
                lista.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar planos: " + e.getMessage());
        }

        return lista;
    }

    public void atualizar(Plano plano) {
        String sql = "UPDATE Plano SET NomePlano=?, Preco=?, DuracaoMeses=?, Descricao=? WHERE PlanoID=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plano.getNomePlano());
            stmt.setDouble(2, plano.getValor());
            stmt.setInt(3, plano.getDuracaoMeses());
            stmt.setString(4, plano.getDescricao());
            stmt.setInt(5, plano.getPlanoID());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar plano: " + e.getMessage());
        }
    }

    public void excluir(int planoID) {
        String sql = "DELETE FROM Plano WHERE PlanoID=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, planoID);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir plano: " + e.getMessage());
        }
    }
}
