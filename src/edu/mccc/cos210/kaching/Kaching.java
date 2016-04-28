package edu.mccc.cos210.kaching;

import java.awt.Dimension;
//import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Kaching extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private FileDialog fdl = new FileDialog(this, "Load", FileDialog.LOAD);
	private FileDialog fds = new FileDialog(this, "Save As...", FileDialog.SAVE);
	private Dimension dimension = new Dimension(100, 75);
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
		fdl.setVisible(false);
		fdl.setFile("*.jpg");
		fdl.setFilenameFilter((dir, name) -> name.endsWith(".jpg"));
		fds.setVisible(false);
		JButton jb = new JButton("Load Image");
		jb.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						try {
							fdl.setVisible(true);
							if (fdl.getFile() != null) {
								String file = fdl.getFile();
								System.out.println(file);
								BufferedImage image = ImageIO.read(); //need make String file into type File
							}
						} catch (Exception e) {
							System.err.println(e.getMessage());
							System.exit(-1);
						}
					}	
				}
			);
		jb.setPreferredSize(dimension);
		jf.add(jb);
		jb = new JButton("Analyze");
		jb.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

					}	
				}
			);
		jb.setPreferredSize(dimension);
		jf.add(jb);
		jb = new JButton("Save As...");
		jb.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					try {
						fds.setVisible(true);
						if (fds.getFile() != null) {
							
						}
					} catch (Exception e) {
						System.err.println(e.getMessage());
						System.exit(-1);
					}
				}	
			}
		);
		jb.setPreferredSize(dimension);
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
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
