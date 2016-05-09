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
		System.out.println("TopX: " +topCoinX);
		System.out.println("TopY : " +topCoinY);
		int ratioLength = blackStripeLength();
		int downDistance = walkDown(index);
		System.out.println("downDistance: " +downDistance);
		int maxLeftDistance = maxLeft(index);
		System.out.println("max Left Distance: " +maxLeftDistance);
		int maxLeftIndex = maxLeftIndex(index);		
		int maxRightDistance = maxRight(index) - 10;
		System.out.println("max right distance: " +maxRightDistance);
		int maxRightIndex = maxRightIndex(index);
		System.out.println("MaxRightX: " +getX(maxRightIndex) +"MaxRightY: " +getY(maxLeftIndex));
		int reliableDiameter = 0;
		if (maxLeftDistance + 6 > maxRightDistance) { // overlap on left
			System.out.println("Overlap on Left");
			reliableDiameter = (maxRightDistance - 2) * 2;
			System.out.println("MaxRight: " +maxRightDistance);
			if (completeTheCircleLeftOverlap(index, ratioLength) != null){
				overlappedCoins.add(new Coin(reliableDiameter, averageRed(topCoinX, topCoinY, reliableDiameter / 2), ratioLength, topCoinX, topCoinY));
				overlappedCoins.add(completeTheCircleLeftOverlap(index, ratioLength));
			}
		} else {
			if (maxRightDistance > maxLeftDistance + 9) { // overlap on right
				System.out.println("Overlap on Right");
				reliableDiameter = (maxLeftDistance - 5) * 2;	
				System.out.println("MaxLeft: " +maxLeftDistance);
				if (completeTheCircleRightOverlap(index, ratioLength) != null) {
					overlappedCoins.add(new Coin(reliableDiameter, averageRed(topCoinX, topCoinY, reliableDiameter / 2), ratioLength, topCoinX, topCoinY));
					overlappedCoins.add(completeTheCircleRightOverlap(index, ratioLength));
				}
			} else {
				if (downDistance > (maxLeftDistance * 2) - 5 || downDistance > (maxRightDistance * 2) - 5
						&& getY(maxLeftIndex) - getY(maxRightIndex) < 10) {
					System.out.println("Overlap is Below");
					//if (completeTheCircleBelowOverlap(index, ratioLength).get(0) != null 
					//		&& completeTheCircleBelowOverlap(index, ratioLength).get(1) != null) {
				//		System.out.println("actualBelowOverlap");
						overlappedCoins.addAll(completeTheCircleBelowOverlap(index, ratioLength));
				//	}
				}
			}
		}
	}
	private Coin completeTheCircleLeftOverlap(int index, int ratioL){
		Point p1 = new Point(getX(maxLeftIndex(index)),getY(maxLeftIndex(index)));
		int px = walkRight((int)p1.x + 2, (int)p1.y) / 5 + (int)p1.x;
		int px2 = walkRight((int)p1.x  + 2, (int)p1.y) / 3 + (int)p1.x;
		Point p2 = new Point(px, getY(walkDownIndex(getIndex(px,(int)p1.y))));
		Point p3 = new Point(px2, getY(walkDownIndex(getIndex(px2,(int)p1.y))));
		System.out.println("Point1: " +p1);
		System.out.println("Point2: " +p2);
		System.out.println("Point3: " +p3);
		new CircleThree();
		CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
		if (cir1 == null) {
			return null;
		}
		int topOfCoinX = (int)cir1.center.x;
		int topOfCoinY = (int)(cir1.center.y - cir1.radius);
		seeCircle(cir1.center, cir1.radius);
		System.out.println("topX: " +topOfCoinX);
		System.out.println("topY: " +topOfCoinY);
		System.out.println("radius: " +cir1.radius);
		return new Coin((int) cir1.radius * 2 - 5, averageRed(topOfCoinX, topOfCoinY, (int)cir1.radius * 2), ratioL, topOfCoinX, topOfCoinY);
	}
	private Coin completeTheCircleRightOverlap(int index, int ratioL){
		Point p1 = new Point(getX(maxRightIndex(index)),getY(maxRightIndex(index)));
		int px =  (int)p1.x -(walkLeft((int)p1.x, (int)p1.y) - (walkLeft((int)p1.x, (int)p1.y) / 5));
		int px2 =  (int)p1.x - (walkLeft((int)p1.x, (int)p1.y) - (walkLeft((int)p1.x, (int)p1.y) / 3));
		Point p2 = new Point(px, getY(walkDownIndex(getIndex(px,(int)p1.y))));
		Point p3 = new Point(px2, getY(walkDownIndex(getIndex(px2,(int)p1.y))));
		System.out.println("Point1: " +p1);
		System.out.println("Point2: " +p2);
		System.out.println("Point3: " +p3);
		new CircleThree();
		CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
		if (cir1 == null) {
			return null;
		}
		int topOfCoinX = (int)cir1.center.x;
		int topOfCoinY = (int)(cir1.center.y - cir1.radius);
		seeCircle(cir1.center, cir1.radius);
		System.out.println("topXz: " +topOfCoinX);
		System.out.println("topYz: " +topOfCoinY);
		System.out.println("radius: " +cir1.radius);
		System.out.println("Center: " +cir1.center);
		return new Coin((int) cir1.radius * 2 - 5, averageRed(topOfCoinX, topOfCoinY, (int)cir1.radius * 2), ratioL, topOfCoinX, topOfCoinY);
	}
	private Vector<Coin> completeTheCircleBelowOverlap(int index, int ratioL) {
		Vector<Coin> localCoins = new Vector<Coin>(2);
		int topY = getY(index);
		int botY = getY(walkDownIndex(index));
		int leftY = getY(maxLeftIndex(index));
		int rightY = getY(maxRightIndex(index));
		System.out.println("Max Left Y:" +leftY);
		System.out.println("Max Right Y:" +rightY);
		Point p1 = null;
		Point p2 = null;
		Point p3 = null;
		if (leftY - topY > botY - leftY && rightY - topY > botY - rightY) { // bigger coin is on bottom
			System.out.println("bigger coin on bottom");
			p1 = new Point(getX(walkDownIndex(index)), getY(walkDownIndex(index)));
			p2 = new Point(getX(maxLeftIndex(index)), getY(maxLeftIndex(index)));
			p3 = new Point(getX(maxRightIndex(index)), getY(maxRightIndex(index)));
			System.out.println("Point1: " +p1);
			System.out.println("Point2: " +p2);
			System.out.println("Point3: " +p3);
			new CircleThree();
			CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
			if (cir1 == null) {
				return null;
			}
			int topOfCoinX = (int)cir1.center.x;
			int topOfCoinY = (int)(cir1.center.y - cir1.radius);
			seeCircle(cir1.center, cir1.radius);
			System.out.println("topXz: " +topOfCoinX);
			System.out.println("topYz: " +topOfCoinY);
			System.out.println("radius: " +cir1.radius);
			localCoins.add(0, (new Coin((int)(cir1.radius * 2), averageRed(topOfCoinX, topOfCoinY, (int)cir1.radius * 2), ratioL, topOfCoinX, topOfCoinY + 5)));
			Point z1 = new Point(getX(index),getY(index));
			Point z2 = new Point(topOfCoinX - walkLeft(topOfCoinX, topOfCoinY), topOfCoinY);
			Point z3 = new Point(topOfCoinX + walkRight(topOfCoinX, topOfCoinY), topOfCoinY);
			System.out.println("Point1: " +p1);
			System.out.println("Point2: " +p2);
			System.out.println("Point3: " +p3);
			CircleThree.Circle cir2 = CircleThree.circleFromPoints(z1, z2, z3);
			int topOfCoinX2 = (int)cir2.center.x;
			int topOfCoinY2 = (int)(cir2.center.y - cir2.radius);
			seeCircle(cir2.center, cir2.radius);
			System.out.println("topXz: " +topOfCoinX2);
			System.out.println("topYz: " +topOfCoinY2);
			System.out.println("radius: " +cir2.radius);
			localCoins.add(1, (new Coin((int)cir2.radius * 2, averageRed(topOfCoinX2, topOfCoinY2, (int)cir2.radius * 2), ratioL, topOfCoinX2, topOfCoinY2)));
		}
		if (botY - leftY > leftY - topY && botY - rightY > rightY - topY) { // bigger coin is on top
			System.out.println("bigger coin on top");
			p1 = new Point(getX(index), getY(index));
			p2 = new Point(getX(maxLeftIndex(index)), getY(maxLeftIndex(index)));
			p3 = new Point(getX(maxRightIndex(index)), getY(maxRightIndex(index)));
			System.out.println("Point1: " +p1);
			System.out.println("Point2: " +p2);
			System.out.println("Point3: " +p3);
			new CircleThree();
			CircleThree.Circle cir1 = CircleThree.circleFromPoints(p1, p2, p3);
			if (cir1 == null) {
				return null;
			}
			int topOfCoinX = (int)cir1.center.x;
			int topOfCoinY = (int)(cir1.center.y - cir1.radius);
			seeCircle(cir1.center, cir1.radius);
			System.out.println("topXz: " +topOfCoinX);
			System.out.println("topYz: " +topOfCoinY);
			System.out.println("radius: " +cir1.radius);
			localCoins.add(0, (new Coin((int) cir1.radius * 2, averageRed(topOfCoinX, topOfCoinY, (int)cir1.radius * 2), ratioL, topOfCoinX, topOfCoinY + 5)));
			int botOfCoinY = (int)(cir1.center.y + cir1.radius);
			p1 = new Point(getX(walkDownIndex(index)),getY(walkDownIndex(index)));
			p2 = new Point(topOfCoinX - walkLeft(topOfCoinX, botOfCoinY), botOfCoinY);
			p3 = new Point(topOfCoinX + walkRight(topOfCoinX, botOfCoinY), botOfCoinY);
			System.out.println("Point1: " +p1);
			System.out.println("Point2: " +p2);
			System.out.println("Point3: " +p3);
			CircleThree.Circle cir2 = CircleThree.circleFromPoints(p1, p2, p3);
			int topOfCoinX2 = (int)cir2.center.x;
			int topOfCoinY2 = (int)(cir2.center.y - cir2.radius);
			seeCircle(cir2.center, cir2.radius);
			System.out.println("topXz: " +topOfCoinX2);
			System.out.println("topYz: " +topOfCoinY2);
			System.out.println("radius: " +cir2.radius);
			localCoins.add(1, (new Coin((int) cir2.radius * 2, averageRed(topOfCoinX2, topOfCoinY2, (int)cir2.radius * 2), ratioL, topOfCoinX2, topOfCoinY2)));
		}
		return localCoins;
	}
	private int walkDown(int i) {
		int x = getX(i);
		int y = getY(i) + 10;
		int height = 1;
		while (pixelOfInterest(x, y)) {
			if (!seenPixel(getIndex(x, y))) {
				seen.add(getIndex(x, y));
			}
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
	private int maxLeftIndex(int i) {
		int x = getX(i);
		int y = getY(i);
		int L = 0;
		int RYtemp = 0;
		while (pixelOfInterest(x, y)) {
			int Ltemp = walkLeft(x, y);
			if (L < Ltemp) {
				L = Ltemp;
				RYtemp = y;
			}
			y++;
		}
		x = x - L;
		return getIndex(x, RYtemp);
	}
	private int maxRightIndex(int i) {
		int x = getX(i);
		int y = getY(i);
		int R = 0;
		int RYtemp = 0;
		while (pixelOfInterest(x, y)) {
			int RXtemp = walkRight(x, y);
			if (R < RXtemp) {
				R = RXtemp;
				RYtemp = y;
			}
			y++;
		}
		x = x + R;
		return getIndex(x, RYtemp);
	}
	private void seeCircle(Point center, double radius) {
		for (int i = 0; i < interest.size(); i++) {
			int index = interest.get(i);
			if (isInCircle((int)center.x, (int)center.y, (int)radius, getX(index), getY(index))) {
				if (!seenPixel(getIndex(getX(index), getY(index)))) {
					seen.add(index);
				}
			}
		}
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
