package edu.mccc.cos210.kaching;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyWindow extends JPanel {
	private static final long serialVersionUID = 1L;
	public MyWindow() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 400));
		setFocusable(true);
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
}