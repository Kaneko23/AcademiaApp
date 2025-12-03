package br.com.academia.view;

import br.com.academia.dao.PlanoDAO;
import br.com.academia.model.Plano;

import javax.swing.*;

public class PlanoForm extends JFrame {

    public PlanoForm() {
        setTitle("Cadastro de Plano");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Nome
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        lblNome.setBounds(20, 20, 150, 25);
        txtNome.setBounds(150, 20, 200, 25);
        add(lblNome); add(txtNome);

        // Preço
        JLabel lblPreco = new JLabel("Preço:");
        JTextField txtPreco = new JTextField();
        lblPreco.setBounds(20, 60, 150, 25);
        txtPreco.setBounds(150, 60, 200, 25);
        add(lblPreco); add(txtPreco);

        // Duração em meses
        JLabel lblMeses = new JLabel("Duração (meses):");
        JTextField txtMeses = new JTextField();
        lblMeses.setBounds(20, 100, 150, 25);
        txtMeses.setBounds(150, 100, 200, 25);
        add(lblMeses); add(txtMeses);

        // Descrição
        JLabel lblDesc = new JLabel("Descrição:");
        JTextArea txtDesc = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtDesc);
        lblDesc.setBounds(20, 140, 150, 25);
        scroll.setBounds(150, 140, 200, 80);
        add(lblDesc);
        add(scroll);

        // Botão Salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 240, 120, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
                Plano plano = new Plano();
                plano.setNomePlano(txtNome.getText());
                plano.setValor(Double.parseDouble(txtPreco.getText()));
                plano.setDuracaoMeses(Integer.parseInt(txtMeses.getText())); // <-- adicionando duração
                plano.setDescricao(txtDesc.getText());

                new PlanoDAO().inserir(plano);
                JOptionPane.showMessageDialog(null, "Plano cadastrado com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Informe valores válidos para preço e duração.");
            }
        });

        setVisible(true);
    }
}
