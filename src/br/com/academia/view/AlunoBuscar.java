package br.com.academia.view;

import br.com.academia.dao.AlunoDAO;
import br.com.academia.model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlunoBuscar extends JFrame {

    private JTable tabela;
    private JTextField campoBusca;
    private DefaultTableModel model;

    public AlunoBuscar() {
        setTitle("Buscar Aluno");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBuscar = new JLabel("Nome ou CPF:");
        lblBuscar.setBounds(20, 20, 150, 25);
        add(lblBuscar);

        campoBusca = new JTextField();
        campoBusca.setBounds(120, 20, 250, 25);
        add(campoBusca);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(380, 20, 100, 25);
        add(btnBuscar);

        model = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Email", "Plano Atual"}, 0);

        tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 70, 540, 260);
        add(scroll);

        btnBuscar.addActionListener(e -> buscar());

        setVisible(true);
    }

    private void buscar() {
        String filtro = campoBusca.getText().trim();

        List<Aluno> alunos = new AlunoDAO().buscar(filtro);

        model.setRowCount(0);
        for (Aluno a : alunos) {
            model.addRow(new Object[]{
                    a.getAlunoID(),
                    a.getNome(),
                    a.getCpf(),
                    a.getEmail(),
                    a.getPlanoAtual() == null ? "Sem plano" : a.getPlanoAtual()
            });
        }
    }
}
