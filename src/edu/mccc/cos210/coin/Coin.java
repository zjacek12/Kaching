package edu.mccc.cos210.coin;

public class Coin {
	private int size = 0;
	private int averageRed = 0;
	private int value = 1;
	private int xPos = 0;
	private int yPos = 0;
	private int ratioLength = 0;
	
	public Coin(int size, int averageRed, int ratioL,int xPos, int yPos) {
		this.ratioLength = ratioL;
		this.size = size;
		this.averageRed = averageRed;
		this.xPos = xPos;
		this.yPos = yPos;
		this.calcValue();
	}
	public Coin(int size, int ratioL,int xPos, int yPos) {
		this.ratioLength = ratioL;
		this.size = size;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	public Coin(int size, int xPos, int yPos) {
		this.size = size;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	/*
	 * Quarter: .995 inch
	 * Nickel: .835 inch
	 * Dime: .705 inch
	 * penny: .75 inch
	*/
	private void calcValue() {
		double doubleRatio = (double)ratioLength;
		double doubleSize = (double)size;
		if (doubleSize / doubleRatio > 0.9) {
			this.setValue(25);
		} else {
			if (doubleSize / doubleRatio > 0.75) {
				this.setValue(5);
			} else {
					this.setValue(10);
			}
		}
	}
	public int getDiameter() {
		return size;
	}
	public void setDiameter(int size) {
		this.size = size;
	}
	public int getaverageRed() {
		return averageRed;
	}
	public void setaverageRed(int averageRed) {
		this.averageRed = averageRed;
	}
	public double getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getXPos() {
		return xPos;
	}
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	public int getYPos() {
		return yPos;
	}
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	public int getRatioLength() {
		return ratioLength;
	}
	public void setRatioLength(int ratioLength) {
		this.ratioLength = ratioLength;
	}
	public String toString() {
		String coinName = null;
		switch (value) {
			case 100: coinName = "Dollar";
				break;
			case 50: coinName = "Quarter Dollar";
				break;
			case 25: coinName = "Quarter";
				break;
			case 10: coinName = "Dime";
				break;
			case 5: coinName = "Nickel";
				break;
			case 1: coinName = "Penny";
				break;
		}
		return coinName +" X: " +xPos + "Y :" +yPos;
	}
}
