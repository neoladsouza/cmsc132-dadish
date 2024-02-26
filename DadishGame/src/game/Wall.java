package game;

import java.awt.*;

public class Wall extends Polygon {
	private Point[] points;
	private int nOfPoints;
	private String id;
	
	public Wall(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		points = this.getPoints();
		nOfPoints = points.length;
		id = "floor";
	}
	
	public String getId() {
		return id;
	}
	
	public void paint(Graphics brush) {
		brush.setColor(Color.BLACK);
		
		int[] xCoords = new int[nOfPoints];
		int[] yCoords = new int[nOfPoints];
		int i = 0;
		
		for (Point point : points) {
			xCoords[i] = (int) point.getX();
			yCoords[i] = (int) point.getY();
			i++;
		}

		brush.drawPolygon(xCoords, yCoords, nOfPoints);
	}

}
