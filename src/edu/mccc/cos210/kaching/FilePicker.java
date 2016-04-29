package edu.mccc.cos210.kaching;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayer;
//import javax.swing.JPanel;

public class FilePicker extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private FileDialog fdl = null;
	private FileDialog fds = null;
	private Dimension dimension = null;
	private BufferedImage image = null;
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
							System.out.println(fileDir + fileName);
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
	@Override
	public void actionPerformed(ActionEvent ae) {
	}
}
