package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import edu.mccc.cos210.ICounter;
import edu.mccc.cos210.coin.Coin;

public class Counter1 extends Counter implements ICounter {
	public Vector<Coin> coins = new Vector<Coin>();
	public List<Integer> seen = new ArrayList<Integer>();
 	public Counter1(BufferedImage bi) {
		super(bi);
	}
	@Override
	public void analyze() {
		for (int i = 1; i < pixelArray.length; i++) {
			if (!seenPixel(i) && pixelOfInterest(i)) {
				int height = walkDown(midpointX(i)); // middle of the top of the coin
				int width = walkSides(midpointX(i));
				System.out.println("X: " +getX(i) +" Y: " +getY(i) +" height: " +height + " width: " +width);
				if (height - 5 < width && height + 5 > width && height > 1) {
					coins.add(new Coin(height, getX(i), getY(i)));
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
	private boolean seenPixel(int index) {
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
		int height = 0;
		int width = 0;
		int L = 0;
		int R = 0;
		while (seenPixel(getIndex(x, y)) && pixelOfInterest(x, y)) {
			walkLeft(x, y);
			walkRight(x, y);
			height++;
			y++;
		}
		L = walkLeft(x, (getY(i) + height / 2)); 
		R = walkRight(x, (getY(i) + height / 2));
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
}
