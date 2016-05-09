package edu.mccc.cos210.kaching;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import edu.mccc.cos210.counter.*;


public class MyWindow extends JPanel {
	private static final long serialVersionUID = 1L;
	public BufferedImage ourImage = null;
	private Kaching kaching;
	public MyWindow(Kaching kaching) {
			this.kaching = kaching;
			setBackground(Color.GRAY);
			FilePicker picker = new FilePicker();
			setPreferredSize(new Dimension(900, 100));
			setFocusable(true);
			add(picker.setUpLoad());
			add(picker.setUpAnalyze());
			add(picker.setUpSave());
		}
		
	
	public String loadCoins(BufferedImage bi) {
		return null;
	}
	
// TODO: make it so we can switch between displays. EX: startup -> animation -> end screen
	public class FilePicker extends JFrame {
		private static final long serialVersionUID = 1L;
		private Dimension dimension = new Dimension(100, 75);
		private JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		private BufferedImage bi = null;
		public FilePicker () {
			
		}
		public JButton setUpLoad () {
		JButton jb = new JButton("Load Image");
		jb.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						jfc.setFileFilter(new FileFilter() {
							@Override
							public boolean accept(File f) {
								if (f.isDirectory()) {
									return true;
								} else {
									return f.getName().toLowerCase().endsWith(".jpg")
									|| f.getName().toLowerCase().endsWith(".jpeg")
									|| f.getName().toLowerCase().endsWith(".png")
									|| f.getName().toLowerCase().endsWith(".gif");
								}
							}
							@Override
							public String getDescription() {
								return "Image support";
							}	
						});
						jfc.showOpenDialog(rootPane);
						int res = jfc.showOpenDialog(null);
						if (res == JFileChooser.APPROVE_OPTION) {
							File file = jfc.getSelectedFile();
							try {
								bi = ImageIO.read(file);
								JOptionPane.showMessageDialog(null, "File selected: " + file.getName());
								BufferedImage icon = ImageIO.read(file);
						        kaching.setPicture(icon);

							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, "File cannot be read.");
								e.printStackTrace();
							}
						}
					}
				}
			);	
		jb.setPreferredSize(dimension);
		return jb;
		}
		public JButton setUpAnalyze () {
			JButton jb = new JButton("Analyze");
			jb.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							if (bi != null) {
								Counter1 counter = new Counter1(bi);
								long startTime = System.currentTimeMillis();
								counter.analyze();
								int result = counter.getResult(counter.coins);
								long endTime = System.currentTimeMillis();
								for (int i : counter.seen) {
									int myBrush = 125;
									bi.setRGB(counter.getX(i), counter.getY(i), myBrush);
								}
								kaching.setPicture(bi);
								System.out.println((endTime - startTime) / 1000.0 + "s");
								JOptionPane.showMessageDialog(null, "We found " + result + " coin(s) in your image.");
							} else {
								JOptionPane.showMessageDialog(null, "Image not selected");
							}
												
						}	
					}
				);
			jb.setPreferredSize(dimension);
			return jb;
		}
		public JButton setUpSave () {
			JButton jb = new JButton("Save As...");
			jb.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						int res = jfc.showSaveDialog(null);
						if (res == JFileChooser.APPROVE_OPTION) {
							File file = jfc.getSelectedFile();
							try {
								ImageIO.write(bi, "jpg", file);
								System.out.println(file);
								System.out.println(bi);
								JOptionPane.showMessageDialog(null, "Image " + " successfully saved.");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}	
				}
			);
			jb.setPreferredSize(dimension);
			return jb;
		}
		public BufferedImage getImage() {
			return this.bi;
		}
		public void setImage(BufferedImage img) {
			this.bi = img;
		}
		public void actionPerformed(ActionEvent ae) {
		}
	}
}
