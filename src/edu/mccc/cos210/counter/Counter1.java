package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import java.util.Vector;
import edu.mccc.cos210.ICounter;
import edu.mccc.cos210.coin.Coin;

public class Counter1 extends Counter implements ICounter {
	public Vector<Coin> coins = new Vector<Coin>();
 	public Counter1(BufferedImage bi) {
		super(bi);
	}
	@Override
	public void analyze() {
		int ratioLength = blackStripeLength();
		for (int i = 0; i < interest.size(); i++) {
			int index = interest.get(i);
			if (!seenPixel(index)) {
				int height = walkDownNoAdd(index);
				if (height > 5 && height > ((ratioLength / 3) * 2)) {
				height = walkDown(midpointX(index)); // middle of the coin
				} else {
					height = 0;
				}
				int width = walkSides(midpointX(index));
				System.out.println("X: " +getX(index) +" Y: " +getY(index) +" height: " +height + " width: " +width);
				if ((width - height < 8 && height - width < 8) && height > width / 5 && width > 10) {
					int averageRed = averageRed(getX(index), getY(index), height);
					coins.add(new Coin(height, averageRed, ratioLength, getX(index), getY(index)));
				} else {
					if ((height > width + 8 || width > height + 8)
							&& (height > width / 5 && width > 10)
							&& (width > height / 5 && height > 10)) {
						System.out.println("I got to counter 2");
						Counter2 count2 = new Counter2(getImage(), getX(index), getY(index));
						count2.analyze();
						coins.addAll(count2.getOverlappedCoins());
					}
				}
			}
		}
	}
	public int averageRed(int topX, int topY, int diameter) {
		int centerX = topX;
		int centerY = topY + (diameter / 2);
		int totalRed = 0;
		int divisor = 0;
		for (int i = 0; i < interest.size(); i++) {
			int index = interest.get(i);
			if (isInCircle(centerX, centerY, diameter / 2, getX(index), getY(index))) {
				totalRed = totalRed + getPixelRed(index);
				divisor++;
			}
		}
		return totalRed / divisor;
	}
	public boolean isInCircle(int centerx, int centery, int radius, int x, int y) {
		int dist = (int) Math.sqrt((centerx - x) * (centerx - x) + (centery - y) * (centery - y));
		return dist <= (radius + 2);
	}
	private int midpointX(int i) { //midpoint btw this pixel and end of coin
		seen.add(i);
		int index = i;
		int length = 0;
		while (!seenPixel(index) && pixelOfInterest(index)) {
			seen.add(index);
			index++;
			length++;
		}
		return length / 2 + i;
	}
	private int walkDown(int i) {
		int x = getX(i);
		int y = getY(i) + 1;
		int height = 1;
		while (!seenPixel(getIndex(x, y)) && pixelOfInterest(x, y)) {
			seen.add(getIndex(x, y));
			height++;
			y++;
		}
		return height;
	}
	private int walkDownNoAdd(int i) {
		int x = getX(i);
		int y = getY(i) + 1;
		int height = 1;
		while (!seenPixel(getIndex(x, y)) && pixelOfInterest(x, y)) {
			height++;
			y++;
		}
		return height;
	}
	
}
