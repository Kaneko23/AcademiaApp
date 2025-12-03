package br.com.academia.view;

import br.com.academia.dao.AlunoDAO;
import br.com.academia.model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlunoList extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;

    public AlunoList() {
        setTitle("Lista de Alunos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        model = new DefaultTableModel(new Object[]{
                "ID", "Nome", "CPF", "Email", "Telefone", "Plano Atual"
        }, 0);

        tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);


        JPanel painelBtn = new JPanel();

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        painelBtn.add(btnEditar);
        painelBtn.add(btnExcluir);

        add(painelBtn, BorderLayout.SOUTH);

        carregarDados();


        btnEditar.addActionListener(e -> editarAluno());

        btnExcluir.addActionListener(e -> excluirAluno());

        setVisible(true);
    }

    private void carregarDados() {
        model.setRowCount(0); // limpa tabela

        AlunoDAO dao = new AlunoDAO();
        List<Aluno> lista = dao.listarComPlano();

        for (Aluno a : lista) {
            model.addRow(new Object[]{
                    a.getAlunoID(),
                    a.getNome(),
                    a.getCpf(),
                    a.getEmail(),
                    a.getTelefone(),
                    a.getPlanoAtual() == null ? "Sem Plano" : a.getPlanoAtual()
            });
        }
    }

    private void editarAluno() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno.");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);
        String nome = (String) tabela.getValueAt(row, 1);
        String cpf = (String) tabela.getValueAt(row, 2);
        String email = (String) tabela.getValueAt(row, 3);
        String telefone = (String) tabela.getValueAt(row, 4);
        String data = ""; // opcional puxar se quiser

        new AlunoFormEditar(id, nome, cpf, email, telefone, data).setVisible(true);
    }

    private void excluirAluno() {
        int row = tabela.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno.");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja excluir este aluno?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new AlunoDAO().excluir(id);
            carregarDados();
        }
    }
}
