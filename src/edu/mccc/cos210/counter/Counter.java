package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import edu.mccc.cos210.ICounter;

public class Counter implements ICounter {
	private BufferedImage image = null;
	private int w = 0;
	private int h = 0;
	public int[] pixelArray = null;
	private int[][] interest = new int[6][w * h];
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
	private void generateRGBArray() {
		pixelArray = new int[w * h];
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
	public int[][] getInterest() {
		return interest;
	}
	public boolean isInterestEmpty() {
		return interest.length == 0;
	}
	@Override
	public void analyze(BufferedImage bi) {
		for (int i = 1; i < pixelArray.length; i++) {
			if (getPixelRed(i - 1) <= (getPixelRed(i) - 5) 
				|| getPixelRed(i - 1) >= (getPixelRed(i) + 5)) {
				int a = 0;
				interest = new int[1][];
				interest[a] = new int[6];
				interest[a][0] = getX(i);
				interest[a][1] = getY(i);
				interest[a][2] = getPixelAlpha(i);
				interest[a][3] = getPixelRed(i);
				interest[a][4] = getPixelGreen(i);
				interest[a][5] = getPixelBlue(i);
				a++;
			}
		}
		numQuarters = 1;
		numDimes = 1;
		numNickles = 1;
		numPennies = 1;
	}
	@Override
	public double getResult(int[] a) {
		return numQuarters * .25
				+ numDimes * .10
				+ numNickles * .5
				+ numPennies * .1;
	}
}
