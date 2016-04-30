package edu.mccc.cos210;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.junit.Test;
import edu.mccc.cos210.counter.Counter;

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
		assertEquals(counter.getPixelRed(counter.getIndex(320, 200)),counter.getPixelRed(320, 200));
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
}
