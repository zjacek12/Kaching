package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import edu.mccc.cos210.ICounter;

public class Counter implements ICounter {
	public double result = 0;
	public int numQuarters = 0;
	public int numDimes = 0;
	public int numNickles = 0;
	public int numPennies = 0;
	@Override
	public void analyze(BufferedImage bi) {
	}
	@Override
	public double getResult(int[] a) {
		return 0;
	}
}
