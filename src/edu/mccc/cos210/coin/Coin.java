package edu.mccc.cos210.coin;

public class Coin {
	public int size = 0;
	public String color = null;
	public int value = 0;
	public int xPos = 0;
	public int yPos = 0;
	public Coin(int size, String color, int value, int xPos, int yPos) {
		this.size = size;
		this.color = color;
		this.value = value;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
