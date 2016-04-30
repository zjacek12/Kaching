package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import edu.mccc.cos210.ICounter;
import edu.mccc.cos210.coin.Coin;

public class Counter1 extends Counter implements ICounter {
	public Vector<Coin> coins = new Vector<Coin>();
	private List<Integer> seenX = new LinkedList<Integer>();
	private List<Integer> seenY = new LinkedList<Integer>();
	public Counter1(BufferedImage bi) {
		super(bi);
	}
	@Override
	public void analyze() {
		for (int i = 1; i < pixelArray.length; i++) {
			if ((getPixelRed(i) < (getPixelGreen(i) + 50) && getPixelRed(i) < (getPixelBlue(i) + 50)) 
					&& (getPixelGreen(i) < (getPixelRed(i) + 50) && getPixelGreen(i) < (getPixelBlue(i) + 50))
					&& (getPixelRed(i) > 75 && getPixelGreen(i) > 75 && getPixelBlue(i) > 75)
					&& !(seenXCointains(getX(i)) && seenYContains(getY(i)))) {
				// calculates middle of top of coin 
				int x1 = getX(i);
				int x2 = i;
				while ((getPixelRed(x2) < (getPixelGreen(x2) + 50) && getPixelRed(x2) < (getPixelBlue(x2) + 50)) 
						&& (getPixelGreen(x2) < (getPixelRed(x2) + 50) && getPixelGreen(x2) < (getPixelBlue(x2) + 50))
						&& (getPixelRed(x2) > 75 && getPixelGreen(x2) > 75 && getPixelBlue(x2) > 75)) {
					x2++;
				}
				x2 = getX(x2);
				int height = walkDown(((x2 - x1) / 2) + x1); // middle of the top of the coin
				i = getIndex(x2, getY(i));
				int width = walkSides(((x2 - x1) / 2) + x1);
				if (height - 5 < width && height + 5 > width) {
					coins.add(new Coin(height, getX(i), getY(i)));
				}
			}
		}
	}
	private boolean seenXCointains(int x) {
		for (int i:seenX) {
			if (i == x) {
				return true;
			}
		}
		return false;
	}
	private boolean seenYContains(int y) {
		for (int i:seenY) {
			if (i == y) {
				return true;
			}
		}
		return false;
	}
	private int walkDown(int i) {
		int x = getX(i);
		int y = getY(i);
		int height = 0;
		while ((getPixelRed(x, y) < (getPixelGreen(x, y) + 50) && getPixelRed(x, y) < (getPixelBlue(x, y) + 50)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 50) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 50))
				&& (getPixelRed(x, y) > 75 && getPixelGreen(x, y) > 75 && getPixelBlue(x, y) > 75)) {
			seenY.add(y);
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
		while ((getPixelRed(x, y) < (getPixelGreen(x, y) + 50) && getPixelRed(x, y) < (getPixelBlue(x, y) + 50)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 50) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 50))
				&& (getPixelRed(x, y) > 75 && getPixelGreen(x, y) > 75 && getPixelBlue(x, y) > 75)) {
			walkLeft(x, y);
			walkRight(x, y);
			height++;
			y++;
		}
		L = walkLeft(x, (getY(i) + height / 2));  // Width at middle of the coin
		R = walkRight(x, (getY(i) + height / 2));
		if (L - 5 < R && L + 5 > R) {
			width = L + R;
		}
		return width;
	}
	private int walkLeft(int x, int y) {
		int x1 = x;
		while ((getPixelRed(x, y) < (getPixelGreen(x, y) + 50) && getPixelRed(x, y) < (getPixelBlue(x, y) + 50)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 50) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 50))
				&& (getPixelRed(x, y) > 75 && getPixelGreen(x, y) > 75 && getPixelBlue(x, y) > 75)) {
			seenX.add(x1);
			x1--;
		}
		return x - x1;
	}
	private int walkRight(int x, int y) {
		int x1 = x;
		while ((getPixelRed(x, y) < (getPixelGreen(x, y) + 50) && getPixelRed(x, y) < (getPixelBlue(x, y) + 50)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 50) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 50))
				&& (getPixelRed(x, y) > 75 && getPixelGreen(x, y) > 75 && getPixelBlue(x, y) > 75)) {
			seenX.add(x1);
			x1++;
		}
		return x1 - x;
	}
}
