package edu.mccc.cos210.kaching;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Kaching extends JFrame {
	private static final long serialVersionUID = 1L;
	private FlowLayout layout = new FlowLayout();
	private JLabel label;
	private Dimension dimension = new Dimension(600, 400);
	public Kaching() {	
		JFrame jf = new JFrame("KACHING");
		jf.setLayout(layout);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyWindow window = new MyWindow(this);
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		jf.add(label, BorderLayout.CENTER);
		jf.add(window);
		jf.getContentPane().setBackground(Color.BLACK);
		jf.pack();
		jf.setResizable(false);
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
	public void setPicture(ImageIcon icon) {
		label.setIcon(icon);	
		label.setMaximumSize(dimension);
	}
}
