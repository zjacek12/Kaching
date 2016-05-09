package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import edu.mccc.cos210.ICounter;
import edu.mccc.cos210.coin.Coin;

public class Counter implements ICounter {
	private BufferedImage image = null;
	private int w = 0;
	private int h = 0;
	public int[] pixelArray = null;
	public List<Integer> interest = new LinkedList<Integer>();
	public List<Integer> seen = new ArrayList<Integer>();
	public int numQuarters = 0;
	public int numDimes = 0;
	public int numNickles = 0;
	public int numPennies = 0;
	public Counter(BufferedImage bi) {
		this.image = bi;
		this.w = image.getWidth();
	    this.h = image.getHeight();
	    generateRGBArray();
	}
	public void generateRGBArray() {
		if (pixelArray == null){
			pixelArray = new int[w * h];
		}
		int i = 0;
		for (int yIndex = 0; yIndex < h ; yIndex++) {
			for (int xIndex = 0; xIndex < w; xIndex++) {
				//System.out.println("Index: " +i);
				pixelArray[i] = image.getRGB(xIndex, yIndex);
				//System.out.println("X: " +xIndex);
				//System.out.println("Y: " +yIndex);
				i++;
			}
		}
		for (int a = 1; a < pixelArray.length; a++) {
			if (pixelOfInterest(a)) {
				interest.add(a);
			}
		}
	}
	public int getPixelAlpha(int x, int y) {
		int index = getIndex(x, y);
		int pixel = pixelArray[index];
	    int alpha = (pixel >> 24) & 0xff;
	    return alpha;
	}
	public int getPixelAlpha(int index) {
		int pixel = pixelArray[index];
	    int alpha = (pixel >> 24) & 0xff;
	    return alpha;
	}
	public int getPixelRed(int x, int y) {
		int index = getIndex(x, y);
		int pixel = pixelArray[index];
	    int red = (pixel >> 16) & 0xff;
	    return red;
	}
	public int getPixelRed(int index) {
		int pixel = pixelArray[index];
	    int red = (pixel >> 16) & 0xff;
	    return red;
	}

	public int getPixelGreen(int x, int y) {
		int index = getIndex(x, y);
		int pixel = pixelArray[index];
	    int green = (pixel >> 8) & 0xff;
	    return green;
	}
	public int getPixelGreen(int index) {
		int pixel = pixelArray[index];
	    int green = (pixel >> 8) & 0xff;
	    return green;
	}
	public int getPixelBlue(int x, int y) {
		int index = getIndex(x, y);
		int pixel = pixelArray[index];
	    int blue = (pixel) & 0xff;
	    return blue;
	}
	public int getPixelBlue(int index) {
		int pixel = pixelArray[index];
	    int blue = (pixel) & 0xff;
	    return blue;
	}
	public int getIndex(int x, int y) {
		int index = x + y * w;
		return index;
	}
	public int getX(int index) {
		int i = index;
		while (i > w) {
			i = i - w;
		}
		return i;
	}
	public int getY(int index) {
		return index / w;
	}
	public int getInterest(int index) {
		return interest.get(index);
	}
	public boolean isInterestEmpty() {
		return interest.size() == 0;
	}
	public int getImageWidth() {
		return w;
	}
	public int getImageHeight() {
		return h;
	}
	public BufferedImage getImage() {
		return this.image;
	}
	public boolean seenPixel(int index) {
		if (seen.contains(index)){
				return true;
		}
		return false;
	}
	public int walkSides(int i) {
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
	public int walkLeft(int x, int y) {
		int x1 = x - 1;
		while (pixelOfInterest(x1, y)) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1--;
		}
		return x - x1;
	}
	public int walkRight(int x, int y) {
		int x1 = x;
		while (pixelOfInterest(x1, y)) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1++;
		}
		return x1 - x;
	}
	public int walkSidesBlackStripe(int i) {
		int x = getX(i);
		int y = getY(i);
		int width = 0;
		int L = 0;
		int R = 0;
		while (getPixelRed(x, y) < 65 && getPixelBlue(x, y) < 60 && getPixelGreen(x, y) < 90) {
			if (!seenPixel(getIndex(x, y))){
				seen.add(getIndex(x, y));
			}
			int Ltemp = walkLeftBlackStripe(x, y);
			int Rtemp = walkRightBlackStripe(x, y);
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
	public int walkLeftBlackStripe(int x, int y) {
		int x1 = x - 1;
		while (getPixelRed(x1, y) < 65 && getPixelBlue(x1, y) < 60 && getPixelGreen(x1, y) < 90) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1--;
		}
		return x - x1;
	}
	public int walkRightBlackStripe(int x, int y) {
		int x1 = x;
		while (getPixelRed(x1, y) < 65 && getPixelBlue(x1, y) < 60 && getPixelGreen(x1, y) < 90) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1++;
		}
		return x1 - x;
	}
	public int blackStripeLength() {
		int length = 0;
		int index = 0;
		while (!(getPixelRed(index) < 60 && getPixelBlue(index) < 60 && getPixelGreen(index) < 80)) {
			index++;
		}
		walkSidesBlackStripe(index + 50);
		while (getPixelRed(index) < 60 && getPixelBlue(index) < 60 && getPixelGreen(index) < 80) {
			length++;
			index++;
		}
		return length;
	}
	public boolean pixelOfInterest(int index) {
		return (getPixelGreen(index) < (getPixelBlue(index) + 70) && getPixelGreen(index) < (getPixelRed(index) + 30)
				&& getPixelBlue(index) < (getPixelGreen(index) + 60)
				/*&& (getPixelRed(index) > 40 && getPixelGreen(index) > 40 && getPixelBlue(index) > 25)*/
				);
	}

	public boolean pixelOfInterest(int x, int y) {
		return ((y < getImageHeight() && x < getImageWidth()) 
				&& getPixelGreen(x, y) < (getPixelBlue(x, y) + 70) && getPixelGreen(x, y) < (getPixelRed(x, y) + 30)
				&& getPixelBlue(x, y) < (getPixelGreen(x, y) + 60)
				/*&& (getPixelRed(x, y) > 40 && getPixelGreen(x, y) > 40 && getPixelBlue(x, y) > 25)*/
				);
	}
	@Override
	public void analyze() {
		for (int i = 1; i < pixelArray.length; i++) {
			if (pixelOfInterest(i)) {
				interest.add(i);
			}
		}
	}
	@Override
	public int getResult(Vector<Coin> a) {
		int total = 0;
		for (Coin coin:a) {
			if (coin != null){
				total += coin.getValue();
			}
		}
		return total;
	}
}
