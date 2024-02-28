package game;

import java.util.ArrayList;

public class ForceRegistry {
    public ArrayList<ForceRegistration> list;
    public final float deltaTime = 1.0f/60.0f;

    public ForceRegistry() {
        list = new ArrayList<>();
    }

    public void updateForces(float deltaTime) {
        for (ForceRegistration fr : list) {
            fr.fg.updateForce(fr.element, deltaTime);
        }
    }

    public void toggleGravity(Gravity gravity, Polygon element) {
        // when does gravity need to be acting on dadish?
        // BUT after having jumped, i.e. up key released (dadish.up == false)
        if (!element.onGround) {
            list.add(new ForceRegistration(gravity, element));
            System.out.println("toggle gravity ON"); // not turning on???
        } else {
            for (ForceRegistration fr : list) {
                if (fr.fg instanceof Gravity && fr.element.onGround == true) {
                    list.remove(fr);
                    System.out.println("toggle gravity OFF");
                    return;
                }
            }
        }  
	}

    public void toggleJump(Jump jumpForce, Polygon element) {
        if (element.jumpStart == true && element.onGround == true) {
            // when up is clicked -> add jump force to registry
            list.add(new ForceRegistration(jumpForce, element));
            System.out.println("toggle jump ON");
        } else {
            for (ForceRegistration fr : list) {
                if (fr.fg instanceof Jump && fr.element.jumpStart == false) {
                    list.remove(fr);
                    System.out.println("toggle jump OFF");
                    return;
                }
            }
        }
    }
}
