package br.com.academia.view;

import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Sistema Academia");
        setSize(320, 330); // tamanho ajustado para comportar todos os botões
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // --- BOTÕES ---

        JButton btnAluno = new JButton("Cadastro de Alunos");
        btnAluno.setBounds(60, 20, 180, 30);
        add(btnAluno);

        JButton btnListar = new JButton("Listar Alunos");
        btnListar.setBounds(60, 60, 180, 30);
        add(btnListar);

        JButton btnPlanos = new JButton("Listar Planos");
        btnPlanos.setBounds(60, 100, 180, 30);
        add(btnPlanos);

        JButton btnCadastroPlano = new JButton("Novo Plano");
        btnCadastroPlano.setBounds(60, 140, 180, 30);
        add(btnCadastroPlano);

        JButton btnMatricula = new JButton("Nova Matrícula");
        btnMatricula.setBounds(60, 180, 180, 30);
        add(btnMatricula);

        JButton btnListarMatricula = new JButton("Listar Matrículas");
        btnListarMatricula.setBounds(60, 220, 180, 30);
        add(btnListarMatricula);

        JButton btnBuscarAluno = new JButton("Buscar Aluno");
        btnBuscarAluno.setBounds(60, 260, 180, 30);
        add(btnBuscarAluno);



        // --- ACTION LISTENERS ---

        btnAluno.addActionListener(e -> new AlunoForm().setVisible(true));

        btnListar.addActionListener(e -> new AlunoList().setVisible(true));

        btnPlanos.addActionListener(e -> new PlanoList().setVisible(true));

        btnCadastroPlano.addActionListener(e -> new PlanoForm().setVisible(true));

        btnMatricula.addActionListener(e -> new MatriculaForm().setVisible(true));

        btnListarMatricula.addActionListener(e -> new MatriculaList().setVisible(true));

        btnBuscarAluno.addActionListener(e -> new AlunoBuscar());

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
