package edu.mccc.cos210.kaching;

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
		jf.pack();
		jf.setResizable(true);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		FilePicker picker = new FilePicker();
		jf.add(picker.setUpLoad());
		jf.add(new MyWindow());
		jf.add(picker.setUpAnalyze());
		jf.add(picker.setUpSave());	
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
