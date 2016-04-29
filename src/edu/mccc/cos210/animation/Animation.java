package edu.mccc.cos210.animation;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Animation extends Canvas {
	private BufferedImage image = null;
	private static final long serialVersionUID = 1L;
	public Animation(BufferedImage img) {
		setPreferredSize(new Dimension(600, 400));
		setFocusable(true);
		this.image = img;
	}
	public void draw() {
		BufferStrategy bs = getBufferStrategy();
		Graphics2D g2dbs = (Graphics2D) bs.getDrawGraphics();
		g2dbs.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2d = (Graphics2D) g2dbs.create();
		AffineTransform gat = new AffineTransform();
		g2dbs.drawRenderedImage(image, gat);
		repaint();
		g2d.dispose();
		g2dbs.dispose();
		bs.show();
	}
}
