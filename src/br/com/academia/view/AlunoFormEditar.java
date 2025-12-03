package br.com.academia.view;

import br.com.academia.dao.AlunoDAO;
import br.com.academia.model.Aluno;

import javax.swing.*;

public class AlunoFormEditar extends JFrame {

    public AlunoFormEditar(int id, String nome, String cpf, String email, String telefone, String nascimento) {

        setTitle("Editar Aluno");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(nome);
        lblNome.setBounds(20, 20, 150, 25);
        txtNome.setBounds(150, 20, 200, 25);
        add(lblNome); add(txtNome);

        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField(cpf);
        lblCPF.setBounds(20, 60, 150, 25);
        txtCPF.setBounds(150, 60, 200, 25);
        add(lblCPF); add(txtCPF);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(email);
        lblEmail.setBounds(20, 100, 150, 25);
        txtEmail.setBounds(150, 100, 200, 25);
        add(lblEmail); add(txtEmail);

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField(telefone);
        lblTelefone.setBounds(20, 140, 150, 25);
        txtTelefone.setBounds(150, 140, 200, 25);
        add(lblTelefone); add(txtTelefone);

        JLabel lblData = new JLabel("Nascimento:");
        JTextField txtData = new JTextField(nascimento);
        lblData.setBounds(20, 180, 150, 25);
        txtData.setBounds(150, 180, 200, 25);
        add(lblData); add(txtData);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 230, 120, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {

            Aluno aluno = new Aluno();
            aluno.setAlunoID(id);
            aluno.setNome(txtNome.getText());
            aluno.setCpf(txtCPF.getText());
            aluno.setEmail(txtEmail.getText());
            aluno.setTelefone(txtTelefone.getText());
            aluno.setDataNascimento(txtData.getText());

            new AlunoDAO().atualizar(aluno);
            JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
            dispose();
        });

        setVisible(true);
    }
}
