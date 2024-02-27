package game;

public class ForceRegistration {
    public ForceGenerator fg;
    public Polygon element;

    public ForceRegistration(ForceGenerator fg, Polygon element) {
        this.fg = fg;
        this.element = element;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } 

        if (!(other instanceof ForceRegistration)) {
            return false;
        }

        ForceRegistration f = (ForceRegistration) other;
        return f.element == this.element && f.fg == this.fg;
    }
}
