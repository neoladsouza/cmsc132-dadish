package game;

public class Jump implements ForceGenerator {
    public Point jump;

    public Jump(Point force) {
        jump = force.clone();
    }

    public void updateForce(Polygon element, float deltaTime) {
        Point p = new Point(jump.x * element.mass, jump.y * element.mass);
        element.addForce(p);
    }
}

