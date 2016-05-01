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
				if (height - 5 < width && height + 5 > width) {
					coins.add(new Coin(height, getX(i), getY(i)));
				}
			}
		}
	}
	private boolean pixelOfInterest(int index) {
		return ((getPixelRed(index) < (getPixelGreen(index) + 45) && getPixelRed(index) < (getPixelBlue(index) + 45)) 
				&& (getPixelGreen(index) < (getPixelRed(index) + 60) && getPixelGreen(index) < (getPixelBlue(index) + 60))
				&& (getPixelBlue(index) < (getPixelRed(index) + 90) && getPixelBlue(index) < (getPixelGreen(index) + 60))
				&& (getPixelRed(index) > 50 && getPixelGreen(index) > 50 && getPixelBlue(index) > 50)
				);
	}
	private boolean pixelOfInterest(int x, int y) {
		return ((y < getImageHeight() && x < getImageWidth()) 
				&& (getPixelRed(x, y) < (getPixelGreen(x, y) + 45) && getPixelRed(x, y) < (getPixelBlue(x, y) + 45)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 70) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 50))
				&& (getPixelBlue(x, y) < (getPixelRed(x, y) + 90) && getPixelBlue(x, y) < (getPixelGreen(x, y) + 60))
				&& (getPixelRed(x, y) > 50 && getPixelGreen(x, y) > 50 && getPixelBlue(x, y) > 50)
				);
	}
	private int midpointX(int i) { //midpoint btw this pixel and end of coin
		//int x1 = getX(i);
		//int y1 = getY(i);
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
		int y = getY(i);
		int height = 0;
		while (pixelOfInterest(x, y)) {
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
		while (pixelOfInterest(x, y)) {
			walkLeft(x, y);
			walkRight(x, y);
			height++;
			y++;
		}
		L = walkLeft(x, (getY(i) + height / 2));  // Width at middle of the coin
		R = walkRight(x, (getY(i) + height / 2));
		width = L + R;
		return width;
	}
	private int walkLeft(int x, int y) {
		int x1 = x;
		while (pixelOfInterest(x1, y)) {
			seen.add(getIndex(x1, y));
	//		seen.add(getIndex(x1, (y + 1)));
	//		seen.add(getIndex(x1, (y - 1)));
	//		seen.add(getIndex(x1, (y + 2)));
	//		seen.add(getIndex(x1, (y - 2)));
			x1--;
		}
	//	int refrenceX = x1 - 1;
	//	int refrenceY = y - 2;
	//	for (int xo = 0; xo <= 1; xo++){
	//		for (int yo = 0; yo <= 4 ; yo++ ) {
	//			seen.add(getIndex(refrenceX - xo, refrenceY + yo));
	//		}
	//	}
		return x - x1;
	}
	private int walkRight(int x, int y) {
		int x1 = x;
		while (pixelOfInterest(x1, y)) {
			seen.add(getIndex(x1, y));
			//seen.add(getIndex(x1, (y + 1)));
			//seen.add(getIndex(x1, (y - 1)));
			//seen.add(getIndex(x1, (y + 2)));
			//seen.add(getIndex(x1, (y - 2)));
			x1++;
		}
		//int refrenceX = x1 + 1;
		//int refrenceY = y - 2;
		//for (int xo = 0; xo <= 1; xo++){
		//	for (int yo = 0; yo <= 4 ; yo++ ) {
	//			seen.add(getIndex(refrenceX + xo, refrenceY + yo));
	//		}
	//	}
		return x1 - x;
	}
}
