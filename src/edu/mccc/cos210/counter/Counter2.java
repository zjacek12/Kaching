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
		int ratioLength = redStripeLength();
		int downDistance = walkDown(index);
		System.out.println("downDistance: " +downDistance);
		int maxLeftDistance = maxLeft(index);
		System.out.println("max Left Distance: " +maxLeftDistance);
		int maxLeftIndex = maxLeftIndex(index);		
		int maxRightDistance = maxRight(index);
		System.out.println("max right distance: " +maxRightDistance);
		int maxRightIndex = maxRightIndex(index);
		System.out.println("MaxRightX: " +getX(maxRightIndex) +"MaxRightY: " +getY(maxLeftIndex));
		int reliableDiameter = 0;
		if (maxLeftDistance + 6 > maxRightDistance) { // overlap on left
			System.out.println("Overlap on Left");
			reliableDiameter = maxRightDistance * 2;
			if (completeTheCircleLeftOverlap(index, ratioLength) != null){
				overlappedCoins.add(new Coin(reliableDiameter, ratioLength, topCoinX, topCoinY));
				overlappedCoins.add(completeTheCircleLeftOverlap(index, ratioLength));
			}
		} else {
			if (maxRightDistance > maxLeftDistance + 5) { // overlap on right
				System.out.println("Overlap on Right");
				reliableDiameter = maxLeftDistance * 2;	
				if (completeTheCircleRightOverlap(index, ratioLength) != null) {
					overlappedCoins.add(new Coin(reliableDiameter, ratioLength, topCoinX, topCoinY));
					overlappedCoins.add(completeTheCircleRightOverlap(index, ratioLength));
				}
			} else {
				if (downDistance > (maxLeftDistance * 2) - 5 || downDistance > (maxRightDistance * 2) - 5
						&& getY(maxLeftIndex) - getY(maxRightIndex) < 10) {
					System.out.println("Overlap is Below");
					if (completeTheCircleBelowOverlap(index, ratioLength).get(0) != null 
							&& completeTheCircleBelowOverlap(index, ratioLength).get(1) != null) {
						overlappedCoins.addAll(completeTheCircleBelowOverlap(index, ratioLength));
					}
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
		seeSides(getIndex(topOfCoinX, topOfCoinY));
		System.out.println("topX: " +topOfCoinX);
		System.out.println("topY: " +topOfCoinY);
		System.out.println("radius: " +cir1.radius);
		return new Coin((int) cir1.radius * 2 - 5, ratioL, topOfCoinX, topOfCoinY);
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
		seeSides(getIndex(topOfCoinX, topOfCoinY));
		System.out.println("topXz: " +topOfCoinX);
		System.out.println("topYz: " +topOfCoinY);
		System.out.println("radius: " +cir1.radius);
		return new Coin((int) cir1.radius * 2 - 5, ratioL, topOfCoinX, topOfCoinY);
	}
	private Vector<Coin> completeTheCircleBelowOverlap(int index, int ratioL) {
		Vector<Coin> localCoins = new Vector<Coin>(2);
		localCoins.setSize(2);
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
			seeSides(getIndex(topOfCoinX, topOfCoinY));
			System.out.println("topXz: " +topOfCoinX);
			System.out.println("topYz: " +topOfCoinY);
			System.out.println("radius: " +cir1.radius);
			localCoins.add(new Coin((int) cir1.radius * 2 - 5, ratioL, topOfCoinX, topOfCoinY));
			p1 = new Point(getX(index),getY(index));
			p2 = new Point(topOfCoinX - walkLeft(topOfCoinX, topOfCoinY), topOfCoinY);
			p3 = new Point(topOfCoinX + walkRight(topOfCoinX, topOfCoinY), topOfCoinY);
			System.out.println("Point1: " +p1);
			System.out.println("Point2: " +p2);
			System.out.println("Point3: " +p3);
			CircleThree.Circle cir2 = CircleThree.circleFromPoints(p1, p2, p3);
			int topOfCoinX2 = (int)cir2.center.x;
			int topOfCoinY2 = (int)(cir2.center.y - cir2.radius);
			seeSides(getIndex(topOfCoinX2, topOfCoinY2));
			System.out.println("topXz: " +topOfCoinX2);
			System.out.println("topYz: " +topOfCoinY2);
			System.out.println("radius: " +cir2.radius);
			localCoins.add(new Coin((int) cir2.radius * 2 - 5, ratioL, topOfCoinX2, topOfCoinY2));
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
			seeSides(getIndex(topOfCoinX, topOfCoinY));
			System.out.println("topXz: " +topOfCoinX);
			System.out.println("topYz: " +topOfCoinY);
			System.out.println("radius: " +cir1.radius);
			localCoins.add(new Coin((int) cir1.radius * 2 - 5, ratioL, topOfCoinX, topOfCoinY));
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
			seeSides(getIndex(topOfCoinX2, topOfCoinY2));
			System.out.println("topXz: " +topOfCoinX2);
			System.out.println("topYz: " +topOfCoinY2);
			System.out.println("radius: " +cir2.radius);
			localCoins.add(new Coin((int) cir2.radius * 2 - 5, ratioL, topOfCoinX2, topOfCoinY2));
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
	public double pythagorean(int x , int y) {
        double c = Math.sqrt((x*x)+(y*y));
        return c;
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
	private int maxRight(int i) {
		int x = getX(i);
		int y = getY(i);
		int R = 0;
		while (pixelOfInterest(x, y)) {
			int Rtemp = walkRight(x, y);
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
		int RYtemp = 0;
		while (pixelOfInterest(x, y)) {
			int RXtemp = walkRight(x, y);
			if (R < RXtemp) {
				R = RXtemp;
				RYtemp = y;
			}
			y++;
		}
		x += R;
		return getIndex(x, RYtemp);
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
	private void seeSides(int i) {
		int x = getX(i);
		int y = getY(i) + 1;
		while (pixelOfInterest(x, y)) {
			walkLeft(x, y);
			walkRight(x, y);
			y++;
		}
	}
	private void seeCircle(Point center, double radius) {
		
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
