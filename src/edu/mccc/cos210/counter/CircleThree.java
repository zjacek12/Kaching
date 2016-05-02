package edu.mccc.cos210.counter;

public class CircleThree
{ 
  static final double TOL = 0.0000001;
 
  public static Circle circleFromPoints(final Point p1, final Point p2, final Point p3)
  {
    final double offset = Math.pow(p2.x,2) + Math.pow(p2.y,2);
    final double bc =   ( Math.pow(p1.x,2) + Math.pow(p1.y,2) - offset )/2.0;
    final double cd =   (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2))/2.0;
    final double det =  (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x)* (p1.y - p2.y); 

    if (Math.abs(det) < TOL) { throw new IllegalArgumentException("Boom"); }

    final double idet = 1/det;

    final double centerx =  (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
    final double centery =  (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
    final double radius = Math.sqrt( Math.pow(p2.x - centerx,2) + Math.pow(p2.y-centery,2));
    
	return new Circle(new Point(centerx,centery),radius);
	}
  
	static class Circle {
	    final Point center;
	    final double radius;
	    public Circle(Point center, double radius)
	    {
	      this.center = center; this.radius = radius;
	    }
	    @Override 
	    public String toString()
	    {
	      return new StringBuilder().append("Center= ").append(center).append(", r=").append(radius).toString();
	    }
	}
	
	static class Point {
	    final double x,y;
	
	    public Point(double x, double y)
	    {
	      this.x = x; this.y = y;
	    }
	    @Override
	    public String toString()
	    {
	      return "("+x+","+y+")";
	    }
	
	 }
	}