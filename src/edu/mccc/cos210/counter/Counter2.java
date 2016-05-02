package edu.mccc.cos210.counter;

import java.awt.image.BufferedImage;
import java.util.Vector;
import edu.mccc.cos210.ICounter;
import edu.mccc.cos210.coin.Coin;
import edu.mccc.cos210.counter.CircleThree.Point;

public class Counter2 extends Counter1 implements ICounter {
	private int topCoinX = 0;
	private int topCoinY = 0;
	private Vector<Coin> overlappedCoins = new Vector<Coin>();
	public Counter2(BufferedImage bi, int x, int y) {
		super(bi);
		this.topCoinX = x;
		this.topCoinY = y;
	}
	@Override
	public void analyze() {
		int index = getIndex(topCoinX, topCoinY);
		int ratioLength = redStripeLength();
		int downDistance = walkDown(index);
		int maxLeftDistance = maxLeft(index);
		int maxLeftIndex = maxLeftIndex(index);
		int maxRightDistance = maxRight(index);
		int maxRightIndex = maxRightIndex(index);
		int reliableDiameter = 0;
		if (maxLeftDistance > maxRightDistance + 5) { // overlap on left
			reliableDiameter = maxRightDistance * 2;
			overlappedCoins.add(new Coin(reliableDiameter, ratioLength, topCoinX, topCoinY));
			overlappedCoins.add(completeTheCircleLeftOverlap(index, ratioLength));
		}
		if (maxRightDistance > maxLeftDistance + 5) { // overlap on right
			reliableDiameter = maxLeftDistance * 2;
			overlappedCoins.add(new Coin(reliableDiameter, ratioLength, topCoinX, topCoinY));
			overlappedCoins.add(completeTheCircleRightOverlap(index, ratioLength));
		}
		if (downDistance > (maxLeftDistance * 2) - 5 || downDistance > (maxRightDistance * 2) - 5
				&& getY(maxLeftIndex) - getY(maxRightIndex) < 10) {
			overlappedCoins.addAll(completeTheCircleBelowOverlap(index, ratioLength));
		}
	}
	private Coin completeTheCircleLeftOverlap(int index, int ratioL){
		Point p1 = new Point(getX(maxLeftIndex(index)),getY(maxLeftIndex(index)));
		int px = walkRight((int)p1.x, (int)p1.y) / 5 + (int)p1.x;
		int px2 = walkRight((int)p1.x, (int)p1.y) / 3 + (int)p1.x;
		Point p2 = new Point(px, getY(walkDownIndex(getIndex(px,(int)p1.y))));
		Point p3 = new Point(px2, getY(walkDownIndex(getIndex(px2,(int)p1.y))));
		new CircleThree();
		CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
		int topOfCoinX = (int)cir1.center.x;
		int topOfCoinY = (int)(cir1.center.y - cir1.radius);
		return new Coin((int) cir1.radius * 2, ratioL, topOfCoinX, topOfCoinY);
	}
	private Coin completeTheCircleRightOverlap(int index, int ratioL){
		Point p1 = new Point(getX(maxRightIndex(index)),getY(maxRightIndex(index)));
		int px = (walkLeft((int)p1.x, (int)p1.y) - (walkLeft((int)p1.x, (int)p1.y) / 5)) + (int)p1.x;
		int px2 = (walkLeft((int)p1.x, (int)p1.y) - (walkLeft((int)p1.x, (int)p1.y) / 3)) + (int)p1.x;
		Point p2 = new Point(px, getY(walkDownIndex(getIndex(px,(int)p1.y))));
		Point p3 = new Point(px2, getY(walkDownIndex(getIndex(px2,(int)p1.y))));
		new CircleThree();
		CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
		int topOfCoinX = (int)cir1.center.x;
		int topOfCoinY = (int)(cir1.center.y - cir1.radius);
		return new Coin((int) cir1.radius * 2, ratioL, topOfCoinX, topOfCoinY);
	}
	private Vector<Coin> completeTheCircleBelowOverlap(int index, int ratioL) {
		Vector<Coin> localCoins = new Vector<Coin>(2);
		localCoins.setSize(2);
		int topY = getY(index);
		int botY = getY(walkDownIndex(index));
		int leftY = getY(maxLeftIndex(index));
		int rightY = getY(maxRightIndex(index));
		Point p1 = null;
		Point p2 = null;
		Point p3 = null;
		if (leftY - topY > botY - leftY && rightY - topY > botY - rightY) { // bigger coin is on bottom
			p1 = new Point(getX(walkDownIndex(index)), getY(walkDownIndex(index)));
			p2 = new Point(getX(maxLeftIndex(index)), getY(maxLeftIndex(index)));
			p3 = new Point(getX(maxRightIndex(index)), getY(maxRightIndex(index)));
			new CircleThree();
			CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
			int topOfCoinX = (int)cir1.center.x;
			int topOfCoinY = (int)(cir1.center.y - cir1.radius);
			localCoins.add(new Coin((int) cir1.radius * 2, ratioL, topOfCoinX, topOfCoinY));
			p1 = new Point(getX(index),getY(index));
			p2 = new Point(topOfCoinX - walkLeft(topOfCoinX, topOfCoinY), topOfCoinY);
			p3 = new Point(topOfCoinX + walkRight(topOfCoinX, topOfCoinY), topOfCoinY);
			CircleThree.Circle cir2 = CircleThree.circleFromPoints(p1, p2, p3);
			int topOfCoinX2 = (int)cir2.center.x;
			int topOfCoinY2 = (int)(cir2.center.y - cir2.radius);
			localCoins.add(new Coin((int) cir2.radius * 2, ratioL, topOfCoinX2, topOfCoinY2));
		}
		if (botY - leftY > leftY - topY && botY - rightY > rightY - topY) { // bigger coin is on top
			p1 = new Point(getX(index), getY(index));
			p2 = new Point(getX(maxLeftIndex(index)), getY(maxLeftIndex(index)));
			p3 = new Point(getX(maxRightIndex(index)), getY(maxRightIndex(index)));
			new CircleThree();
			CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
			int topOfCoinX = (int)cir1.center.x;
			int topOfCoinY = (int)(cir1.center.y - cir1.radius);
			localCoins.add(new Coin((int) cir1.radius * 2, ratioL, topOfCoinX, topOfCoinY));
			int botOfCoinY = (int)(cir1.center.y + cir1.radius);
			p1 = new Point(getX(walkDownIndex(index)),getY(walkDownIndex(index)));
			p2 = new Point(topOfCoinX - walkLeft(topOfCoinX, botOfCoinY), botOfCoinY);
			p3 = new Point(topOfCoinX + walkRight(topOfCoinX, botOfCoinY), botOfCoinY);
			CircleThree.Circle cir2 = CircleThree.circleFromPoints(p1, p2, p3);
			int topOfCoinX2 = (int)cir2.center.x;
			int topOfCoinY2 = (int)(cir2.center.y - cir2.radius);
			localCoins.add(new Coin((int) cir2.radius * 2, ratioL, topOfCoinX2, topOfCoinY2));
		}
		return localCoins;
	}
	private int walkDown(int i) {
		int x = getX(i);
		int y = getY(i) + 1;
		int height = 1;
		while (!seenPixel(getIndex(x, y)) && pixelOfInterest(x, y)) {
			seen.add(getIndex(x, y));
			height++;
			y++;
		}
		return height;
	}
	private int walkDownIndex(int i) {
		int x = getX(i);
		int y = getY(i) + 1;
		while (pixelOfInterest(x, y)) {
			if (!seenPixel(getIndex(x, y))){
				seen.add(getIndex(x, y));
			}
			y++;
		}
		return getIndex(x, y);
	}
	private int maxLeft(int i) {
		int x = getX(i);
		int y = getY(i);
		int L = 0;
		while (pixelOfInterest(x, y)) {
			int Ltemp = walkLeft(x, y);
			if (L < Ltemp) {
				L = Ltemp;
			}
			y++;
		}
		return L;
	}
	private int maxLeftIndex(int i) {
		int x = getX(i);
		int y = getY(i);
		int L = 0;
		while (pixelOfInterest(x, y)) {
			int Ltemp = walkLeft(x, y);
			if (L < Ltemp) {
				L = Ltemp;
			}
			y++;
		}
		return getIndex(x, y);
	}
	private int maxRight(int i) {
		int x = getX(i);
		int y = getY(i);
		int R = 0;
		while (pixelOfInterest(x, y)) {
			int Rtemp = walkLeft(x, y);
			if (R < Rtemp) {
				R = Rtemp;
			}
			y++;
		}
		return R;
	}
	private int maxRightIndex(int i) {
		int x = getX(i);
		int y = getY(i);
		int R = 0;
		while (pixelOfInterest(x, y)) {
			int Rtemp = walkLeft(x, y);
			if (R < Rtemp) {
				R = Rtemp;
			}
			y++;
		}
		return getIndex(x, y);
	}
	private int walkRight(int x, int y) {
		int x1 = x;
		while (pixelOfInterest(x1, y)) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1++;
		}
		return x1 - x;
	}
	private int walkLeft(int x, int y) {
		int x1 = x - 1;
		while (pixelOfInterest(x1, y)) {
			if (!seenPixel(getIndex(x1, y))){
				seen.add(getIndex(x1, y));
			}
			x1--;
		}
		return x - x1;
	}
	public int getTopCoinX() {
		return topCoinX;
	}
	public void setTopCoinX(int topCoinX) {
		this.topCoinX = topCoinX;
	}
	public int getTopCoinY() {
		return topCoinY;
	}
	public void setTopCoinY(int topCoinY) {
		this.topCoinY = topCoinY;
	}
	public Vector<Coin> getOverlappedCoins() {
		return overlappedCoins;
	}
	public void setOverlappedCoins(Vector<Coin> overlappedCoins) {
		this.overlappedCoins = overlappedCoins;
	}
	
}
