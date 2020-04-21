package model.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.entidades.Memoria;
import model.entidades.MemoriaObserver;

public class Display extends JPanel implements MemoriaObserver {
	private static final long serialVersionUID = 1L;

	private JLabel label;

	public Display() {
		Memoria.getInstancia().registraObservador(this);
		setBackground(new Color(45, 50, 50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 30));

		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		add(label);
	}

	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
}
