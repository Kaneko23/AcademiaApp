package br.com.academia.view;

import br.com.academia.dao.PlanoDAO;
import br.com.academia.model.Plano;

import javax.swing.*;
import java.awt.*;

public class PlanoFormEditar extends JFrame {

    public PlanoFormEditar(int id) {
        setTitle("Editar Plano");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblPreco = new JLabel("Preço:");
        JTextField txtPreco = new JTextField();

        JLabel lblMeses = new JLabel("Meses de duração:");
        JTextField txtMeses = new JTextField();

        JLabel lblDesc = new JLabel("Descrição:");
        JTextField txtDesc = new JTextField();

        JButton btnSalvar = new JButton("Salvar");

        add(lblNome);  add(txtNome);
        add(lblPreco); add(txtPreco);
        add(lblMeses); add(txtMeses);
        add(lblDesc);  add(txtDesc);
        add(btnSalvar);


        PlanoDAO dao = new PlanoDAO();
        Plano p = null;

        for (Plano plano : dao.listar()) {
            if (plano.getPlanoID() == id) {
                p = plano;
                break;
            }
        }

        if (p != null) {
            txtNome.setText(p.getNomePlano());
            txtPreco.setText(String.valueOf(p.getValor()));
            txtMeses.setText(String.valueOf(p.getDuracaoMeses()));
            txtDesc.setText(p.getDescricao());
        }

        Plano planoFinal = p;

        btnSalvar.addActionListener(e -> {
            planoFinal.setNomePlano(txtNome.getText());
            planoFinal.setValor(Double.parseDouble(txtPreco.getText()));
            planoFinal.setDuracaoMeses(Integer.parseInt(txtMeses.getText()));
            planoFinal.setDescricao(txtDesc.getText());

            dao.atualizar(planoFinal);
            JOptionPane.showMessageDialog(null, "Plano atualizado com sucesso!");
            dispose();
        });
    }
}
