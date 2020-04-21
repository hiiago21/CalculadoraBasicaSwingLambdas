package model.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.entidades.Memoria;

public class Teclado extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private final Color COR_CINZA = new Color(68,68,68);
	private final Color COR_CINZA_CLARO = new Color(99,99,99);
	private final Color COR_LARANJA = new Color(242,163,60);
	
	public Teclado() {
		GridBagLayout  gb= new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		setLayout(gb);
		gbc.weightx =1;
		gbc.weighty=1;
		
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridwidth = 3;
		adicionarBotao("AC", COR_CINZA, gbc, 0, 0);
		gbc.gridwidth = 1;
		adicionarBotao("/", COR_LARANJA, gbc, 3, 0);
		
		
		adicionarBotao("7", COR_CINZA_CLARO, gbc, 0, 1);
		adicionarBotao("8", COR_CINZA_CLARO, gbc, 1, 1);
		adicionarBotao("9", COR_CINZA_CLARO, gbc, 2, 1);
		adicionarBotao("*", COR_LARANJA, gbc, 3, 1);
		
		adicionarBotao("4", COR_CINZA_CLARO, gbc, 0, 2);
		adicionarBotao("5", COR_CINZA_CLARO, gbc, 1, 2);
		adicionarBotao("6", COR_CINZA_CLARO, gbc, 2, 2);
		adicionarBotao("-", COR_LARANJA, gbc, 3, 2);
		
		adicionarBotao("3", COR_CINZA_CLARO, gbc, 0, 3);
		adicionarBotao("2", COR_CINZA_CLARO, gbc, 1, 3);
		adicionarBotao("1", COR_CINZA_CLARO, gbc, 2, 3);
		adicionarBotao("+", COR_LARANJA, gbc, 3, 3);
		
		gbc.gridwidth = 2;
		adicionarBotao("0", COR_CINZA_CLARO, gbc, 0, 4);
		gbc.gridwidth = 1;
		adicionarBotao(",", COR_CINZA_CLARO, gbc, 2, 4);
		adicionarBotao("=", COR_LARANJA, gbc, 3, 4);
	}

	private void adicionarBotao(String txt, Color color, GridBagConstraints gbc, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		Botao bt = new Botao(txt, color);
		bt.addActionListener(this);
		add(bt, gbc);		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() instanceof JButton) {
				JButton bt = (JButton) e.getSource();
				Memoria.getInstancia().processaComando(bt.getText());
		 }
	}
}
