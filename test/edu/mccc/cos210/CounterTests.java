package edu.mccc.cos210;

import static org.junit.Assert.assertEquals;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.junit.Test;
import edu.mccc.cos210.coin.Coin;
import edu.mccc.cos210.counter.Counter1;

public class CounterTests {
	@Test
	public void coinVectorContains5() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./5.jpg"));
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
			System.out.println("AvgRed: " +i.getaverageRed());
			System.out.println("ratioLength: " + i.getRatioLength());
		}
		System.out.println("Number of Coins: " +counter.getResult(counter.coins));
		assertEquals(5, counter.getResult(counter.coins));
	}
	@Test
	public void coinVectorContains4() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./4.jpg"));
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
			System.out.println("AvgRed: " +i.getaverageRed());
			System.out.println("ratioLength: " + i.getRatioLength());
		}
		System.out.println("Number of Coins: " +counter.getResult(counter.coins));
		assertEquals(4, counter.getResult(counter.coins));
	}
	@Test
	public void coinVectorContainsBottomO4() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./BottomO4.jpg"));
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
			System.out.println("AvgRed: " +i.getaverageRed());
			System.out.println("ratioLength: " + i.getRatioLength());
		}
		System.out.println("Number of Coins: " +counter.getResult(counter.coins));
		assertEquals(4, counter.getResult(counter.coins));
	}
	@Test
	public void coinVectorContainsBottomO5() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./BottomO5.jpg"));
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
			System.out.println("AvgRed: " +i.getaverageRed());
			System.out.println("ratioLength: " + i.getRatioLength());
		}
		System.out.println("Number of Coins: " +counter.getResult(counter.coins));
		assertEquals(5, counter.getResult(counter.coins));
	}
	@Test
	public void coinVectorContainsLeftO6() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./LeftO6.jpg"));
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
			System.out.println("AvgRed: " +i.getaverageRed());
			System.out.println("ratioLength: " + i.getRatioLength());
		}
		System.out.println("Number of Coins: " +counter.getResult(counter.coins));
		assertEquals(6, counter.getResult(counter.coins));
	}
	@Test
	public void coinVectorContainsTopO4() {
		BufferedImage bi = null;
		if (bi == null){
			try {
				bi = ImageIO.read(new File("./TopO4.jpg"));
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
			System.out.println("AvgRed: " +i.getaverageRed());
			System.out.println("ratioLength: " + i.getRatioLength());
		}
		System.out.println("Number of Coins: " +counter.getResult(counter.coins));
		assertEquals(4, counter.getResult(counter.coins));
	}
}
