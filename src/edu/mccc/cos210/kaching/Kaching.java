package edu.mccc.cos210.kaching;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Kaching extends JFrame {
	private static final long serialVersionUID = 1L;
	private FlowLayout layout = new FlowLayout();
	public Kaching() {	
		JFrame jf = new JFrame("KACHING");
		jf.setLayout(layout);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new MyWindow());
		jf.getContentPane().setBackground(Color.BLACK);
		jf.pack();
		jf.setResizable(true);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] sa) {	
		EventQueue.invokeLater(Kaching::new);
	}
	public void algorithmPriority(BufferedImage bi) {	
	}
	public void startAnimation() {	
	}
	public void restart() {	
	}
}
