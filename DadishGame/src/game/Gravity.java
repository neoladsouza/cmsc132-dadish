package game;

public class Gravity implements ForceGenerator {
    public Point gravity;

    public Gravity(Point force) {
        gravity = force.clone();
    }

    public void updateForce(Polygon element, float deltaTime) {
        Point p = new Point(gravity.x * element.mass, gravity.y * element.mass);
        element.addForce(p);
    }
}
