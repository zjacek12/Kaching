package edu.mccc.cos210;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.junit.Test;
import edu.mccc.cos210.coin.Coin;
import edu.mccc.cos210.counter.Counter;
import edu.mccc.cos210.counter.Counter1;

public class CounterTests {
	
	@Test
	public void indexCorrect() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./85738000.jpg"));
			} catch (Exception ex) {
				System.out.println("something fucked up loading img");
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		Counter counter = new Counter(bi);
		assertEquals(118,counter.getX(counter.getIndex(118, 51)));
		assertEquals(115,counter.getY(counter.getIndex(3, 115)));
		assertEquals(counter.getIndex(118, 51), 32758);
		assertEquals(counter.getX(32758), 118);
		assertEquals(counter.getY(32758), 51);
		//System.out.println(counter.getPixelRed(320,200));
	}
	@Test
	public void hasInterestPoints() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./85738000.jpg"));
			} catch (Exception ex) {
				System.out.println("something fucked up loading img");
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		Counter counter = new Counter(bi);
		counter.analyze();
		assertFalse(counter.isInterestEmpty());
		//for (int a = 0; a < counter.interest.size(); a++) {
		//System.out.println("(X,Y) :" +"(" +counter.getInterest(a, 0) + "," +counter.getInterest(a, 1) +")");
		//System.out.println("RGB:");
		//System.out.println("RED: " +counter.getInterest(a,3));
		//System.out.println("GREEN: " +counter.getInterest(a,4));
		//System.out.println("BLUE: " +counter.getInterest(a,5));
		//}
	}
	@Test
	public void coinVectorContainsACoin() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./85738000.jpg"));
			} catch (Exception ex) {
				System.out.println("something fucked up loading img");
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		Counter1 counter = new Counter1(bi);
		counter.analyze();
		//System.out.println(counter.coins.size());
		//for (Coin i:counter.coins) {
		//	System.out.println("X: " +i.getXPos() +" Y: " +i.getYPos());
		//	System.out.println("Diameter: " + i.getDiameter());
		//}
		assertEquals(counter.coins.size(), 7);
	}
	@Test
	public void coinVectorContainsACoinRedBackground() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./images4.jpg"));
			} catch (Exception ex) {
				System.out.println("something fucked up loading img");
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		Counter1 counter = new Counter1(bi);
		counter.analyze();
		//System.out.println("RED: "+ counter.coins.size());
		//for (Coin i:counter.coins) {
		//	System.out.println("X: " +i.getXPos() +" Y: " +i.getYPos());
		//	System.out.println("Diameter: " + i.getDiameter());
		//}
		assertEquals(counter.coins.size(), 4);
	}
	@Test
	public void coinVectorContainsCoinsRedBackground() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./images.jpg"));
			} catch (Exception ex) {
				System.out.println("something fucked up loading img");
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		Counter1 counter = new Counter1(bi);
		counter.analyze();
		//System.out.println("CoinVectorSize: "+ counter.coins.size());
		//for (Coin i:counter.coins) {
		//	System.out.println("X: " +i.getXPos() +" Y: " +i.getYPos());
		//	System.out.println("Diameter: " + i.getDiameter());
		//}
		assertEquals(1, counter.coins.size());
	}
	@Test
	public void coinVectorContainsCoinsStriped() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./coinWpattern.jpg"));
			} catch (Exception ex) {
				System.out.println("something fucked up loading img");
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		Counter1 counter = new Counter1(bi);
		counter.analyze();
		System.out.println("CoinVectorSize: "+ counter.coins.size());
		for (Coin i:counter.coins) {
			System.out.println("X: " +i.getXPos() +" Y: " +i.getYPos());
			System.out.println("Diameter: " + i.getDiameter());
		}
		assertEquals(5, counter.coins.size());
	}
}
