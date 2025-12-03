package br.com.academia.view;

import br.com.academia.dao.AlunoDAO;
import br.com.academia.model.Aluno;

import javax.swing.*;
import java.util.regex.Pattern;

public class AlunoForm extends JFrame {

    public AlunoForm() {
        setTitle("Cadastro de Alunos");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        lblNome.setBounds(20, 20, 150, 25);
        txtNome.setBounds(150, 20, 200, 25);
        add(lblNome); add(txtNome);

        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField();
        lblCPF.setBounds(20, 60, 150, 25);
        txtCPF.setBounds(150, 60, 200, 25);
        add(lblCPF); add(txtCPF);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        lblEmail.setBounds(20, 100, 150, 25);
        txtEmail.setBounds(150, 100, 200, 25);
        add(lblEmail); add(txtEmail);

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();
        lblTelefone.setBounds(20, 140, 150, 25);
        txtTelefone.setBounds(150, 140, 200, 25);
        add(lblTelefone); add(txtTelefone);

        JLabel lblData = new JLabel("Nascimento (AAAA-MM-DD):");
        JTextField txtData = new JTextField("2000-01-01");
        lblData.setBounds(20, 180, 200, 25);
        txtData.setBounds(220, 180, 130, 25);
        add(lblData); add(txtData);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 230, 120, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {

            // ----------------------------
            // 1) VALIDAR CAMPOS OBRIGATÓRIOS
            // ----------------------------
            if (txtNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O nome é obrigatório!");
                return;
            }

            if (txtCPF.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O CPF é obrigatório!");
                return;
            }

            if (txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O email é obrigatório!");
                return;
            }

            if (txtTelefone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O telefone é obrigatório!");
                return;
            }

            if (txtData.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "A data de nascimento é obrigatória!");
                return;
            }

            // ----------------------------
            // 2) VALIDAR CPF FORMATO (somente números, 11 dígitos)
            // ----------------------------
            String cpf = txtCPF.getText().replace(".", "").replace("-", "").trim();
            if (!cpf.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(null, "CPF inválido! Digite somente números, 11 dígitos.");
                return;
            }

            // ----------------------------
            // 3) IMPEDIR CPF REPETIDO
            // ----------------------------
            AlunoDAO dao = new AlunoDAO();
            if (dao.cpfExiste(cpf)) {
                JOptionPane.showMessageDialog(null, "Já existe um aluno cadastrado com esse CPF!");
                return;
            }

            // ----------------------------
            // 4) VALIDAR FORMATO DA DATA
            // ----------------------------
            if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", txtData.getText())) {
                JOptionPane.showMessageDialog(null, "A data deve estar no formato AAAA-MM-DD");
                return;
            }

            // ----------------------------
            // 5) CADASTRAR ALUNO
            // ----------------------------
            Aluno aluno = new Aluno();
            aluno.setNome(txtNome.getText());
            aluno.setCpf(cpf);
            aluno.setEmail(txtEmail.getText());
            aluno.setTelefone(txtTelefone.getText());
            aluno.setDataNascimento(txtData.getText());

            dao.inserir(aluno);

            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");

            dispose();
        });

        setVisible(true);
    }
}
