package edu.mccc.cos210.kaching;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyWindow extends JPanel {
	private static final long serialVersionUID = 1L;
	public MyWindow() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(1024, 768));
		setFocusable(true);
	}
	
	private BufferedImage loadTex() {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("./change.png"));
		} catch (Exception ex) {
			System.out.println("something fucked up loading img");
			ex.printStackTrace();
			System.exit(-1);
		}
		return bi;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		AffineTransform gat = new AffineTransform();
		gat.translate(getWidth() / 2.0, getHeight() / 2);
		gat.scale(1.0, 1.0);
		g2d.transform(gat);
		g2d.setColor(Color.BLACK);
		g2d.drawImage(
				loadTex(),
				null,
				-512,
				-384
			);
		
		g2d.dispose();
	}
// TODO: make it so we can switch between displays. EX: startup -> animation -> end screen
	@SuppressWarnings("unused")
	private class MyBufferedImage extends BufferedImage {
		public MyBufferedImage(int width, int height, int type) {
			super(width, height, type);
			paintSelf(createGraphics());
		}

		private void paintSelf(Graphics2D g2d) {
		}
	}
}