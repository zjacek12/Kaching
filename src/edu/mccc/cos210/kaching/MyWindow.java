package edu.mccc.cos210.kaching;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyWindow extends JPanel  implements ActionListener {
	private static final long serialVersionUID = 1L;
	public BufferedImage image;
	public MyWindow() {
		setBackground(Color.WHITE);
		FilePicker picker = new FilePicker();
		setPreferredSize(new Dimension(900, 600));
		setFocusable(true);
		add(picker.setUpLoad());
		add(picker.setUpAnalyze());
		add(picker.setUpSave());
	}
	public BufferedImage loadImage() {
		FilePicker picker = new FilePicker();
		BufferedImage bi = (BufferedImage) picker.getImage();
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./change.png"));
			} catch (Exception ex) {
				System.out.println("something fucked up loading img");
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		return bi;
	}
	public String loadCoins(BufferedImage bi) {
		return null;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.BLACK);
		g2d.drawImage(
			loadImage(),
			null,
			0,
			0
			);
		g2d.dispose();
	}
// TODO: make it so we can switch between displays. EX: startup -> animation -> end screen
	public class FilePicker extends JFrame {
		private static final long serialVersionUID = 1L;
		private FileDialog fdl = null;
		private FileDialog fds = null;
		private Dimension dimension = null;
		private BufferedImage image = null;
		private String fileLocation = null;
		public FilePicker () {
				fdl = new FileDialog(this, "Load", FileDialog.LOAD);
				fds = new FileDialog(this, "Save As...", FileDialog.SAVE);
				dimension = new Dimension(100, 75);
		}
		public FilePicker (int dimensionx, int dimensiony) {
				fdl = new FileDialog(this, "Load", FileDialog.LOAD);
				fds = new FileDialog(this, "Save As...", FileDialog.SAVE);
				dimension = new Dimension(dimensionx, dimensiony);
		}
		public JButton setUpLoad () {
		fdl.setVisible(false);
		fdl.setFile("*.jpg");
		fdl.setFilenameFilter((dir, name) -> name.endsWith(".jpg"));
		JButton jb = new JButton("Load Image");
		jb.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						try {
							fdl.setVisible(true);
							if (fdl.getFile() != null) {
								String fileDir = fdl.getDirectory();
								String fileName = fdl.getFile();
								fileLocation = fileDir + fileName;
								System.out.println(fileLocation);
								image = (BufferedImage)ImageIO.read(new File(fileDir + fileName));
							}
						} catch (Exception e) {
							System.err.println(e.getMessage());
							System.exit(-1);
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

						}	
					}
				);
			jb.setPreferredSize(dimension);
			return jb;
		}
		public JButton setUpSave () {
			fds.setVisible(false);
			JButton jb = new JButton("Save As...");
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
			return jb;
		}
		public BufferedImage getImage() {
			return image;
		}
		public void setImage(BufferedImage img) {
			this.image = img;
		}
		public void actionPerformed(ActionEvent ae) {
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}


}

