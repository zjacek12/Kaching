package edu.mccc.cos210.kaching;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private JPanel holder;
	private JLabel value;
	private BufferedImage image;
	private ImageCanvas display;
	private int count = 0;
	private Dimension dimension = new Dimension(100, 50);
	public Kaching() {	
		JFrame jf = new JFrame("KACHING");
		jf.setLayout(layout);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyWindow window = new MyWindow(this);
		label = new JPanel();
		label.setSize(480, 480);
		label.setBackground(Color.BLACK);
		jf.add(label, BorderLayout.CENTER);
		value = new JLabel("");
		value.setFont(new Font("KaFont", Font.PLAIN, 40));
		value.setForeground(Color.WHITE);
		holder = new JPanel();
		holder.setPreferredSize(new Dimension(900, 60));
		holder.setBackground(Color.BLACK);
		jf.add(holder);
		holder.add(value);
		jf.add(window);
		jf.getContentPane().setBackground(Color.BLACK);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setSize(800, 700);
		
	}
	public static void main(String[] sa) {	
		EventQueue.invokeLater(Kaching::new);
	}
	public void algorithmPriority(BufferedImage bi) {	
	}
	public void startAnimation() {	
	}
	public void rewrite(String num) {
		value.setText(num);
	}
	public void setPicture(BufferedImage image) {
		System.out.println("setPicture is working");
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
				System.out.println(Wh + " " + Ht + " " + Rt);
			}
			if(img.getWidth() < img.getHeight()){
				Rt = (double) img.getHeight() / 480;
				Wh = (int) (img.getWidth() / Rt);
				Ht = 480;
				System.out.println("Width is smaller");
			}
			
			Image tmp = img.getScaledInstance(Wh, Ht, Image.SCALE_SMOOTH);
		    dimg = new BufferedImage(Wh, Ht, BufferedImage.TYPE_INT_ARGB);

		    Graphics2D g2d = dimg.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
		}

		@Override
		public Dimension getPreferredSize() {
			System.out.println("getPreferredSize is working");
			return dimg == null ? new Dimension(200, 200) : new Dimension(dimg.getWidth(), dimg.getHeight());
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			System.out.println("paint is working");
			if (dimg != null) {
				int x = (getWidth() - dimg.getWidth()) / 2;
				int y = (getHeight() - dimg.getHeight()) / 2;
				g.drawImage(dimg, 0, 0, this);
			}
		}

	}
}
