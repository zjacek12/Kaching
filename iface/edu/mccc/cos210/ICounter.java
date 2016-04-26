package edu.mccc.cos210;

import java.awt.image.BufferedImage;

public interface ICounter {
	public void analyze(BufferedImage bi);
	public double getResult(int[] coins);
}
