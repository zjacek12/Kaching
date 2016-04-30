package edu.mccc.cos210.counter;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import edu.mccc.cos210.ICounter;
import edu.mccc.cos210.coin.Coin;

public class Counter1 extends Counter implements ICounter {
	public Vector<Coin> coins = new Vector<Coin>();
	private List<Point> seenPoint = new LinkedList<Point>();
	public Counter1(BufferedImage bi) {
		super(bi);
	}
	@Override
	public void analyze() {
		for (int i = 1; i < pixelArray.length; i++) {
			if ((getPixelRed(i) < (getPixelGreen(i) + 60) && getPixelRed(i) < (getPixelBlue(i) + 60)) 
					&& (getPixelGreen(i) < (getPixelRed(i) + 60) && getPixelGreen(i) < (getPixelBlue(i) + 60))
					&& (getPixelRed(i) > 30 && getPixelGreen(i) > 30 && getPixelBlue(i) > 30)
					&& !(seenPixel(getX(i), getY(i)))) {
				int height = walkDown(midpointX(i)); // middle of the top of the coin
				int width = walkSides(midpointX(i));
			//	System.out.println("X: " +getX(i) +" Y: " +getY(i) +" height: " +height + " width: " +width);
				if (height - 5 < width && height + 5 > width) {
					coins.add(new Coin(height, getX(i), getY(i)));
				}
			}
		}
	}
	private int midpointX(int i) { //midpoint btw this pixel and end of coin
		int x1 = getX(i);
		int y1 = getY(i);
		int x2 = getX(i) + 1;
		while ((y1 < getImageHeight() && x1 < getImageWidth()) 
				&& (getPixelRed(x2, y1) < (getPixelGreen(x2, y1) + 60) && getPixelRed(x2, y1) < (getPixelBlue(x2, y1) + 60)) 
				&& (getPixelGreen(x2, y1) < (getPixelRed(x2, y1) + 60) && getPixelGreen(x2, y1) < (getPixelBlue(x2, y1) + 60))
				&& (getPixelRed(x2, y1) > 30 && getPixelGreen(x2, y1) > 30 && getPixelBlue(x2, y1) > 30)) {
			seenPoint.add(new Point(x2, y1));
			x2++;
		}
		return getIndex((((x2 - x1) / 2) + x1), y1);
	}
	private boolean seenPixel(int x, int y) {
		for (Point p:seenPoint) {
			if (x == p.getX() && y == p.getY()) {
				return true;
			}
		}
		return false;
	}
	private int walkDown(int i) {
		int x = getX(i);
		int y = getY(i);
		int height = 0;
		while ((y < getImageHeight() && x < getImageWidth()) 
				&& (getPixelRed(x, y) < (getPixelGreen(x, y) + 60) && getPixelRed(x, y) < (getPixelBlue(x, y) + 60)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 60) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 60))
				&& (getPixelRed(x, y) > 30 && getPixelGreen(x, y) > 30 && getPixelBlue(x, y) > 30)) {
			seenPoint.add(new Point(x, y));
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
		while ((y < getImageHeight() && x < getImageWidth()) 
				&& (getPixelRed(x, y) < (getPixelGreen(x, y) + 60) && getPixelRed(x, y) < (getPixelBlue(x, y) + 60)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 60) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 60))
				&& (getPixelRed(x, y) > 30 && getPixelGreen(x, y) > 30 && getPixelBlue(x, y) > 30)) {
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
		while ((y < getImageHeight() && x1 < getImageWidth()) 
				&& (getPixelRed(x1, y) < (getPixelGreen(x1, y) + 60) && getPixelRed(x1, y) < (getPixelBlue(x1, y) + 60)) 
				&& (getPixelGreen(x1, y) < (getPixelRed(x1, y) + 60) && getPixelGreen(x1, y) < (getPixelBlue(x1, y) + 60))
				&& (getPixelRed(x1, y) > 30 && getPixelGreen(x1, y) > 30 && getPixelBlue(x1, y) > 30)) {
			seenPoint.add(new Point(x1, y));
			x1--;
		}
		seenPoint.add(new Point(x1 - 1, y));
		seenPoint.add(new Point(x1 - 2, y));
		seenPoint.add(new Point(x1 - 3, y));
		return x - x1;
	}
	private int walkRight(int x, int y) {
		int x1 = x;
		while ((y < getImageHeight() && x1 < getImageWidth()) 
				&& (getPixelRed(x1, y) < (getPixelGreen(x1, y) + 60) && getPixelRed(x1, y) < (getPixelBlue(x1, y) + 60)) 
				&& (getPixelGreen(x1, y) < (getPixelRed(x1, y) + 60) && getPixelGreen(x1, y) < (getPixelBlue(x1, y) + 60))
				&& (getPixelRed(x1, y) > 30 && getPixelGreen(x1, y) > 30 && getPixelBlue(x1, y) > 30)) {
			seenPoint.add(new Point(x1, y));
			x1++;
		}
		seenPoint.add(new Point(x1 + 1, y));
		seenPoint.add(new Point(x1 + 2, y));
		seenPoint.add(new Point(x1 + 3, y));
		return x1 - x;
	}
}
