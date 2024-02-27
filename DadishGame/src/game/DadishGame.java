// Authors : Neola and Carlo
// Test

package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;

class DadishGame extends Game {
	private Polygon[] elements; // representing not dadish

	// declaring element
	private Radish dadish;
	private Wall floor;
	private Wall ceiling;

	public DadishGame() {
		super("Dadish!", 800, 600);
		this.setFocusable(true);
		this.requestFocus(); // focus on component window

		int s = 5;
		Point[] dadishPoints = new Point[] {
				/*
				 * new Point(0,0), new Point(0, sideLength), new Point(sideLength,sideLength),
				 * new Point(sideLength,0),
				 */
				new Point(2.5 * s, 0 * s), new Point(0.5 * s, 1 * s), new Point(0 * s, 1.5 * s),
				new Point(0 * s, 2.5 * s), new Point(1 * s, 3.5 * s), new Point(2.5 * s, 4 * s),
				new Point(2 * s, 4.5 * s), new Point(2.5 * s, 4 * s), new Point(3 * s, 4.5 * s),
				new Point(2.5 * s, 4 * s), new Point(3.5 * s, 3.5 * s), new Point(4.5 * s, 2.5 * s),
				new Point(4.5 * s, 1.5 * s), new Point(4 * s, 1 * s), };

		double groundLevel = (height * 3 / 4) + (s / 2);
		
		Point dadishPosition = new Point(width / 2, groundLevel - 10);
		double inRotation = 180;

		dadish = new Radish(dadishPoints, dadishPosition, inRotation);
		this.addKeyListener(dadish);

		Point[] floorPoints = new Point[] { new Point(0, 0), new Point(0, 20), new Point(width * 1.5, 20), 
				new Point(width * 1.5, 0) };
		
		
		floor = new Wall(floorPoints, new Point(0, groundLevel + 10), 0);
		ceiling = new Wall(floorPoints, new Point(0, 10), 0);

		elements = new Polygon[] { floor, ceiling };
	}

	public void paint(Graphics brush) {
		brush.setColor(new Color(52, 159, 209));
		brush.fillRect(0, 0, width, height); // make the sky
		brush.setColor(new Color(73, 161, 120)); 
		brush.fillRect(0, height * 3 / 4, width, height / 4); // make the grass

		// iterate through each non-dadish element
		for (int i = 0; i < elements.length; i++) {
			floor.paint(brush);
			ceiling.paint(brush);
			dadish.move();

			// does dadish collide with another element?
			if (elements[i].collides(dadish)) {
//				System.out.println("dadish collided with floor");
//				System.out.println(dadish.position.x + " " + dadish.position.y);
				
				// dadish position gets reset to his initial position
				if (elements[i] instanceof Wall) {
					// if its the ground - dadish should stay on the ground
					// .. how do i make him jump?
					dadish.reset();
				}
			}

			dadish.paint(brush);
		}
	}

	public static void main(String[] args) {
		DadishGame a = new DadishGame();
		a.repaint(); // calls paint method
	}
}