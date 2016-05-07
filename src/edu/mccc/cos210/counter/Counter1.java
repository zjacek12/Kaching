package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import edu.mccc.cos210.ICounter;
import edu.mccc.cos210.coin.Coin;

public class Counter1 extends Counter implements ICounter {
	private Vector<Coin> coins = new Vector<Coin>();
	private List<Integer> seen = new ArrayList<Integer>();
 	public Counter1(BufferedImage bi) {
		super(bi);
	}
	@Override
	public void analyze() {
		int ratioLength = redStripeLength();
		for (int i = 1; i < pixelArray.length; i++) {
			if (!seenPixel(i) && pixelOfInterest(i)) {
				int height = walkDown(midpointX(i)); // middle of the top of the coin
				int width = walkSides(midpointX(i));
				System.out.println("X: " +getX(i) +" Y: " +getY(i) +" height: " +height + " width: " +width);
				if (height - 5 < width && height + 5 > width && height > 10 && width > 10) {
					coins.add(new Coin(height, ratioLength, getX(i), getY(i)));
				} else {
					if ((height > width + 8 || width > height + 8)
							&& (height > 10 && width > 10) ) {
						Counter2 count2 = new Counter2(getImage(), getX(i), getY(i));
						coins.addAll(count2.getOverlappedCoins());
					}
				}
			}
		}
	}
	private int midpointX(int i) { //midpoint btw this pixel and end of coin
		seen.add(i);
		int index = i;
		int length = 0;
		while (pixelOfInterest(index)) {
			seen.add(index);
			index++;
			length++;
		}
		return length / 2 + i;
	}
	public boolean seenPixel(int index) {
		if (seen.contains(index)){
				return true;
		}
		return false;
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
	private int walkSides(int i) {
		int x = getX(i);
		int y = getY(i);
		int width = 0;
		int L = 0;
		int R = 0;
		while (seenPixel(getIndex(x, y)) && pixelOfInterest(x, y)) {
			int Ltemp = walkLeft(x, y);
			int Rtemp = walkRight(x, y);
			if (L < Ltemp) {
				L = Ltemp;
			}
			if (R < Rtemp) {
				R = Rtemp;
			}
			y++;
		}
		width = L + R;
		return width;
	}
	private int walkLeft(int x, int y) {
		int x1 = x - 1;
		while (pixelOfInterest(x1, y)) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1--;
		}
		return x - x1;
	}
	private int walkRight(int x, int y) {
		int x1 = x;
		while (pixelOfInterest(x1, y)) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1++;
		}
		return x1 - x;
	}
	public Vector<Coin> getCoins() {
		return coins;
	}
	public void setCoins(Vector<Coin> coins) {
		this.coins = coins;
	}
	public List<Integer> getSeen() {
		return seen;
	}
	public void setSeen(List<Integer> seen) {
		this.seen = seen;
	}
	public void add(int index) {
		this.seen.add(index);
	}
	
}
