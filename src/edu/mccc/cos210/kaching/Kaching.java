package edu.mccc.cos210.kaching;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import edu.mccc.cos210.animation.Animation;



public class Kaching extends JFrame {
	private static final long serialVersionUID = 1L;
	private FlowLayout layout = new FlowLayout();
	public Kaching() {	
		JFrame jf = new JFrame("KACHING");
		FilePicker picker = new FilePicker();
		Animation animation = new Animation(picker.getImage());
		jf.setLayout(layout);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new MyWindow());
		jf.add(animation, BorderLayout.CENTER);
		jf.getContentPane().setBackground(Color.BLACK);
		jf.pack();
		jf.setResizable(true);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.add(picker.setUpLoad());
		jf.add(picker.setUpAnalyze());
		jf.add(picker.setUpSave());
		animation.createBufferStrategy(2);
		animation.setIgnoreRepaint(false);
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
