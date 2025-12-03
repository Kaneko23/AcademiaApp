package br.com.academia.view;

import br.com.academia.dao.MatriculaDAO;
import br.com.academia.model.Matricula;

import javax.swing.*;
import java.time.LocalDate;

public class MatriculaForm extends JFrame {

    private JTextField campoAluno;
    private JTextField campoPlano;
    private JTextField campoData;
    private JComboBox<String> campoStatus;

    public MatriculaForm() {
        setTitle("Nova Matrícula");
        setSize(350, 280);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblAluno = new JLabel("Aluno ID:");
        lblAluno.setBounds(20, 20, 100, 25);
        add(lblAluno);

        campoAluno = new JTextField();
        campoAluno.setBounds(120, 20, 160, 25);
        add(campoAluno);

        JLabel lblPlano = new JLabel("Plano ID:");
        lblPlano.setBounds(20, 60, 100, 25);
        add(lblPlano);

        campoPlano = new JTextField();
        campoPlano.setBounds(120, 60, 160, 25);
        add(campoPlano);

        JLabel lblData = new JLabel("Data Início:");
        lblData.setBounds(20, 100, 100, 25);
        add(lblData);

        campoData = new JTextField(LocalDate.now().toString()); // Padrão: hoje
        campoData.setBounds(120, 100, 160, 25);
        add(campoData);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20, 140, 100, 25);
        add(lblStatus);

        campoStatus = new JComboBox<>(new String[]{"Ativa", "Pausada", "Cancelada"});
        campoStatus.setBounds(120, 140, 160, 25);
        add(campoStatus);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100, 190, 120, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
                Matricula m = new Matricula();
                m.setAlunoID(Integer.parseInt(campoAluno.getText()));
                m.setPlanoID(Integer.parseInt(campoPlano.getText()));
                m.setDataInicio(LocalDate.parse(campoData.getText()));  // CORRIGIDO
                m.setStatus((String) campoStatus.getSelectedItem());

                new MatriculaDAO().inserir(m);

                JOptionPane.showMessageDialog(null, "Matrícula cadastrada com sucesso!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
