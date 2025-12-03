package br.com.academia.view;

import br.com.academia.dao.MatriculaDAO;
import br.com.academia.model.Matricula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MatriculaList extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;

    public MatriculaList() {

        setTitle("Lista de Matrículas");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Aluno ID", "Plano ID", "Data", "Status"}, 0);
        tabela = new JTable(model);

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();

        JButton btnAtualizar = new JButton("Atualizar Status");
        JButton btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);

        add(painelBotoes, BorderLayout.SOUTH);

        carregar();

        // Atualizar status
        btnAtualizar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma matrícula!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);
            String novoStatus = JOptionPane.showInputDialog(null, "Novo status (Ativa/Cancelada):");

            if (novoStatus == null || novoStatus.isEmpty()) return;

            new MatriculaDAO().atualizarStatus(id, novoStatus);

            carregar();
        });

        // Excluir
        btnExcluir.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma matrícula!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            new MatriculaDAO().excluir(id);
            carregar();
        });

        setVisible(true);
    }

    private void carregar() {
        model.setRowCount(0);

        List<Matricula> lista = new MatriculaDAO().listar();

        for (Matricula m : lista) {
            model.addRow(new Object[]{
                    m.getMatriculaID(),
                    m.getAlunoID(),
                    m.getPlanoID(),
                    m.getDataInicio(),
                    m.getStatus()
            });
        }
    }
}
