package model.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;

	
	public Calculadora() {
		organizarLayOut();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350,325);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}


	private void organizarLayOut() {
		setLayout(new BorderLayout());
		
		Display ds = new Display();
		ds.setPreferredSize(new Dimension(230,60));
		add(ds, BorderLayout.NORTH);
		
		Teclado tc = new Teclado();
		add(tc, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new Calculadora();
	}
}
