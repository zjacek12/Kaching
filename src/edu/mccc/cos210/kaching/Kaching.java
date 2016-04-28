package edu.mccc.cos210.kaching;

//import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Kaching extends JFrame {
	private static final long serialVersionUID = 1L;
	private FileDialog fd = new FileDialog(this, "Load", FileDialog.LOAD);
	public Kaching() {	
		JFrame jf = new JFrame("KACHING");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new MyWindow());
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		JButton jb = new JButton("Load Image");
		jb.setSize(100, 75);
		jf.add(jb);
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
