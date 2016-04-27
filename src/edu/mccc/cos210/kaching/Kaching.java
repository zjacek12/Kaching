package edu.mccc.cos210.kaching;

//import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


public class Kaching {
	public Kaching() {	
		JFrame jf = new JFrame("KACHING");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new MyWindow());
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] sa) {	
		EventQueue.invokeLater(Kaching::new);
	}
	public void saveImage(BufferedImage bi) {	
	}
	public void algorithmPriority(BufferedImage bi) {	
	}
	public void filePicker() {	
	}
	public void startAnimation() {	
	}
	public void restart() {	
	}

}
