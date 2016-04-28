package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import edu.mccc.cos210.ICounter;

public class Counter implements ICounter {
	public int[] pixelArray = null;
	public int numQuarters = 0;
	public int numDimes = 0;
	public int numNickles = 0;
	public int numPennies = 0;
	public Counter(BufferedImage bi) {
		for (int yIndex = 0; yIndex < 768 ; yIndex++) {
			for (int xIndex = 0; xIndex < 1028; xIndex++) {
				bi.getRaster().getDataElements(xIndex, yIndex, null);
			}
		}
	}
	@Override
	public void analyze(BufferedImage bi) {
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
