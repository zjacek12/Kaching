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
		int ratioLength = redStripeLength();
		for (int i = 0; i < interest.size(); i++) {
			//if (!seenPixel(i) && pixelOfInterest(i)) {
			int index = interest.get(i);
			if (!seenPixel(index)) {
				int height = walkDownNoAdd(index);
				if (height > 5) {
				height = walkDown(midpointX(index)); // middle of the coin
				} else {
					height = 0;
				}
				int width = walkSides(midpointX(index));
				System.out.println("X: " +getX(index) +" Y: " +getY(index) +" height: " +height + " width: " +width);
				if ((width - height < 10 && height - width < 10) && height > 10 && width > 10) {
					coins.add(new Coin(height, ratioLength, getX(index), getY(index)));
				} else {
					if ((height > width + 8 || width > height + 8)
							&& (height > 10 && width > 10) ) {
						System.out.println("I got to counter 2");
						Counter2 count2 = new Counter2(getImage(), getX(index), getY(index));
						count2.analyze();
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
		while (!seenPixel(index) && pixelOfInterest(index)) {
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
		if (y - getY(i) < 10){
			width = 0;
		}
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
