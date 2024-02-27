package game;

import java.util.ArrayList;

public class ForceRegistry {
    public ArrayList<ForceRegistration> list;

    public ForceRegistry() {
        list = new ArrayList<>();
    }

    public void updateForces(float deltaTime) {
        for (ForceRegistration fr : list) {
            fr.fg.updateForce(fr.element, deltaTime);
        }
    }
}
