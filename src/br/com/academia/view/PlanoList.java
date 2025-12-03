package br.com.academia.view;

import br.com.academia.dao.PlanoDAO;
import br.com.academia.model.Plano;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlanoList extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;

    public PlanoList() {
        setTitle("Lista de Planos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Preço", "Meses", "Descrição"}, 0
        );

        tabela = new JTable(model);

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        JPanel painel = new JPanel();
        painel.add(btnEditar);
        painel.add(btnExcluir);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(painel, BorderLayout.SOUTH);

        carregar();

        // BOTÃO EDITAR
        btnEditar.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                int id = (int) tabela.getValueAt(linha, 0);
                new PlanoFormEditar(id).setVisible(true);
            }
        });

        // BOTÃO EXCLUIR
        btnExcluir.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                int id = (int) tabela.getValueAt(linha, 0);
                new PlanoDAO().excluir(id);
                carregar();
                JOptionPane.showMessageDialog(null, "Plano excluído com sucesso!");
            }
        });
    }

    private void carregar() {
        model.setRowCount(0);
        PlanoDAO dao = new PlanoDAO();

        for (Plano p : dao.listar()) {
            model.addRow(new Object[]{
                    p.getPlanoID(),
                    p.getNomePlano(),
                    p.getValor(),
                    p.getDuracaoMeses(),
                    p.getDescricao()
            });
        }
    }
}
