package game;

import java.awt.*;
import java.awt.event.*;

public class Radish extends Polygon implements KeyListener {
	// going to handle making all the radishes, from dadish to his kids
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	// private final int MAX_JUMP_HEIGHT = 30;

	private Point[] points;
	private int nOfPoints;
	
	private Point initialPosition;
	
	// status for moving, stationary, jumping
	private boolean onGround;
	private Jump jumpForce;

	public Radish(Point[] inShape, Point inPosition, double inRotation, Jump jump) {
		super(inShape, inPosition, inRotation);
		up = false;
		down = false;
		left = false;
		right = false;
		points = this.getPoints();
		nOfPoints = points.length;
		initialPosition = inPosition.clone(); // create a copy of the point
		onGround = true;
		jumpForce = jump;
		mass = 1;
	}
	
	public void reset() {
//		System.out.println(initialPosition.x + " " + initialPosition.y);
		this.position.x = initialPosition.x;
		this.position.y = initialPosition.y;
		points = this.getPoints();
	}

	public boolean getOnGround() {
		return onGround;
	}

	public void paint(Graphics brush) {
		points = this.getPoints();
		brush.setColor(new Color(231, 55, 119)); // pink

		// need to process a Point[] array into x and y arrays
		// maybe make some eyes and differently colored parts

		int[] xCoords = new int[nOfPoints];
		int[] yCoords = new int[nOfPoints];
		int i = 0;

		for (Point point : points) {
			xCoords[i] = (int) point.getX();
			yCoords[i] = (int) point.getY();
			i++;
		}

		brush.fillPolygon(xCoords, yCoords, nOfPoints); // draw
	}

	// inner class - as an instance variable or local var in paint

	public void move() {
		int stepSize = 5;
		int jumpSize = 10 * stepSize;
		// current position is given by this.position.x and this.position.y
		if (right) {
			this.position.x = (this.position.x + stepSize);
			points = this.getPoints();
		}

		if (left) {
			this.position.x = (this.position.x - stepSize);
			points = this.getPoints();
		}

		if (up) {
			// CALLS JUMP FORCE
			// ---crashes---
			/*if (this.position.y == initialPosition.y) {
				// this.position.y = (this.position.y - jumpSize);
				jumpSize = 30;
				this.position.y = (this.position.y - jumpSize);
				points = this.getPoints();
			}

			jumpSize -= stepSize;
			points = this.getPoints();

			if (this.position.y >= initialPosition.y) {
				this.position.y = initialPosition.y;
				points = this.getPoints();
			}*/

			// can only jump when on the ground
			if (this.position.y == initialPosition.y) {
				onGround = false;
				// this.position.y = (this.position.y - jumpSize);
				jumpForce.updateForce(this, 10);
			}

			points = this.getPoints();
		}

		if (down) {
			while (this.position.y != initialPosition.y) {
				this.position.y = (this.position.y + jumpSize);
				points = this.getPoints();
			}		
			onGround = true;	
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
			// dadish goes up
			// once he goes up, determine how fast he falls back down
			// seperate movement between up and down, left and right
			// decrease Y by jumpHeight -> increment Y by to fall back down until bro hits the ground
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		return;
	}
}
