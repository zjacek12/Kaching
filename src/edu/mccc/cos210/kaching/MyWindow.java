package edu.mccc.cos210.kaching;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class MyWindow extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public BufferedImage ourImage = null;
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
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./change.png"));
				ourImage = bi;
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
		private Dimension dimension = new Dimension(100, 75);
		private JFileChooser jfc = new JFileChooser();
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
							//System.out.println(file);
							try {
								bi = ImageIO.read(file);
								JOptionPane.showMessageDialog(null, "File selected: " + file.getAbsolutePath());
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
								counter.analyze();
								//counter.getResult(counter.coins);
								//System.out.println(counter.getResult(counter.coins));
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}
