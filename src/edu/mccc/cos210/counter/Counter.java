package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
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
	public int redStripeLength() {
		int length = 0;
		int index = 0;
		while (!(getPixelRed(index) > getPixelGreen(index) + 200 && getPixelRed(index) > getPixelBlue(index) + 200)) {
			index++;
		}
		while (getPixelRed(index) > getPixelGreen(index) + 200 && getPixelRed(index) > getPixelBlue(index) + 200) {
			length++;
			index++;
		}
		return length;
	}
	public boolean pixelOfInterest(int index) {
		return ((getPixelRed(index) < (getPixelGreen(index) + 55) && getPixelRed(index) < (getPixelBlue(index) + 55)) 
				&& (getPixelGreen(index) < (getPixelRed(index) + 70) && getPixelGreen(index) < (getPixelBlue(index) + 50))
				&& (getPixelBlue(index) < (getPixelRed(index) + 90) && getPixelBlue(index) < (getPixelGreen(index) + 60))
				/*&& (getPixelRed(index) > 50 && getPixelGreen(index) > 50 && getPixelBlue(index) > 50)*/
				);
	}

	public boolean pixelOfInterest(int x, int y) {
		return ((y < getImageHeight() && x < getImageWidth()) 
				&& (getPixelRed(x, y) < (getPixelGreen(x, y) + 55) && getPixelRed(x, y) < (getPixelBlue(x, y) + 55)) 
				&& (getPixelGreen(x, y) < (getPixelRed(x, y) + 70) && getPixelGreen(x, y) < (getPixelBlue(x, y) + 50))
				&& (getPixelBlue(x, y) < (getPixelRed(x, y) + 90) && getPixelBlue(x, y) < (getPixelGreen(x, y) + 60))
				/*&& (getPixelRed(x, y) > 50 && getPixelGreen(x, y) > 50 && getPixelBlue(x, y) > 50)*/
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
