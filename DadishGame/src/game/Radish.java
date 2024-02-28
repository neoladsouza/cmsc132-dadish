package game;

import java.awt.*;
import java.awt.event.*;

public class Radish extends Polygon implements KeyListener {
	// going to handle making all the radishes, from dadish to his kids
	public boolean up;
	// private boolean down;
	private boolean left;
	private boolean right;
	
	// private final int MAX_JUMP_HEIGHT = 30;

	private Point[] points;
	private int nOfPoints;
	
	private Point initialPosition;
	
	// status for moving, stationary, jumping
	private Jump jumpForce;
	private Gravity gravityForce;

	public ForceRegistry globalRegistry;

	public Radish(Point[] inShape, Point inPosition, double inRotation, Jump jump, Gravity gravity, ForceRegistry globalRegistry) {
		super(inShape, inPosition, inRotation);
		up = false;
		// down = false;
		left = false;
		right = false;
		points = this.getPoints();
		nOfPoints = points.length;
		initialPosition = inPosition.clone(); // create a copy of the point
		onGround = false; // only true when we start at the ground
		jumpStart  = false;
		jumpForce = jump;
		gravityForce = gravity;
		setMass(0.5f);
		this.globalRegistry = globalRegistry;
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
		// int jumpSize = 10 * stepSize;
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
			// can only jump when on the ground
			/*if (this.position.y == initialPosition.y) {
				onGround = false;
				// this.position.y = (this.position.y - jumpSize);
				
			}*/
			
			// points = this.getPoints();
			jumpStart = true;
			globalRegistry.toggleJump(jumpForce, this); // jump is ON
			globalRegistry.toggleGravity(gravityForce, this); // gravity is OFF
			// globalRegistry.list.add(new ForceRegistration(jumpForce, this));
			// globalRegistry.list.remove(globalRegistry.list.size() - 1);
		} else {
			// when up gets released, gravity gets toggled BECAUSE WE'RE NOT ON THE GROUND ANYMORE
			jumpStart = false;
			// onGround = false;
			globalRegistry.toggleJump(jumpForce, this); // jump is OFF
			globalRegistry.toggleGravity(gravityForce, this); // gravity is ON
			// globalRegistry.toggleGravity(gravityForce, this);
		}

		/*if (down) {
			// while (this.position.y != initialPosition.y) {
				// this.position.y = (this.position.y + jumpSize);
				// points = this.getPoints();
			// }	
			this.position.y = initialPosition.y;
			this.position.x = initialPosition.x;
			points = this.getPoints();
			onGround = true;	
		}*/
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
			System.out.println("up pressed");
			// dadish goes up
			// once he goes up, determine how fast he falls back down
			// seperate movement between up and down, left and right
			// decrease Y by jumpHeight -> increment Y by to fall back down until bro hits the ground
		}

		/*if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}*/
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
			System.out.println("up released");
		}

		/*if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}*/
	}

	public void keyTyped(KeyEvent e) {
		return;
	}
}
