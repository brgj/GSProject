package objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity extends Thing {
    private List<Bomb> activeBombs;
    public int firePower;
    public int numBombs;

    public Entity(BufferedImage img) {
        super(img);
        firePower = 1;
        numBombs = 1;
        activeBombs = new ArrayList<Bomb>();
    }

    public abstract void move(int direction);

    public void kill() {
        destroyed = true;
    }

    public boolean checkBombs() {
        for(int i = 0; i < activeBombs.size(); i++) {
            if(activeBombs.get(i).isDestroyed()) {
                activeBombs.remove(i);
                i--;
            }
        }
        return activeBombs.size() < numBombs;
    }

    public Bomb setBomb() {
        if(checkBombs()) {
            Bomb b = new Bomb(1000, firePower, x, y);
            activeBombs.add(b);
            return b;
        }
        return null;
    }
}
