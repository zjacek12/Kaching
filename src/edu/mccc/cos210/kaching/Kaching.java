package edu.mccc.cos210.kaching;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;


public class Kaching extends JFrame {
	private static final long serialVersionUID = 1L;
	private FlowLayout layout = new FlowLayout();
	private JPanel label;
	private BufferedImage image;
	private ImageCanvas display;
	private int count = 0;
	private Dimension dimension = new Dimension(600, 400);
	public Kaching() {	
		JFrame jf = new JFrame("KACHING");
		jf.setLayout(layout);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyWindow window = new MyWindow(this);
		label = new JPanel();
		label.setSize(480, 480);
		label.setBackground(Color.BLACK);
		jf.add(label, BorderLayout.CENTER);
		jf.add(window);
		jf.getContentPane().setBackground(Color.BLACK);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setSize(800, 640);
		
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
	public void setPicture(BufferedImage image) {
		if(count == 1) {
			count = 0;
			label.remove(display);
		}
		display = new ImageCanvas(image);
		label.setBackground(Color.WHITE);
		label.add(display);
		SwingUtilities.updateComponentTreeUI(label);
		count = 1;
	}
	public class ImageCanvas extends Canvas {

		public BufferedImage img = null;
		public BufferedImage dimg = null;
		
		public ImageCanvas(BufferedImage bimg) {
			int Wh = 0;
			int Ht = 0;
			double Rt = 0;
			img = bimg;
			if(img.getWidth() >= img.getHeight()){
				Rt = (double) img.getWidth() / 480;
				Ht = (int) (img.getHeight() / Rt);
				Wh = 480;
			}
			if(img.getWidth() < img.getHeight()){
				Rt = (double) img.getHeight() / 480;
				Wh = (int) (img.getWidth() / Rt);
				Ht = 480;
			}
			
			Image tmp = img.getScaledInstance(Wh, Ht, Image.SCALE_SMOOTH);
		    dimg = new BufferedImage(Wh, Ht, BufferedImage.TYPE_INT_ARGB);

		    Graphics2D g2d = dimg.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
		}

		@Override
		public Dimension getPreferredSize() {
			return dimg == null ? new Dimension(200, 200) : new Dimension(dimg.getWidth(), dimg.getHeight());
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (dimg != null) {
				int x = (getWidth() - dimg.getWidth()) / 2;
				int y = (getHeight() - dimg.getHeight()) / 2;
				g.drawImage(dimg, 0, 0, this);
			}
		}

	}
}
