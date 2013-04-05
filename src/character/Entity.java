package character;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity implements Runnable{
    public BufferedImage sprite;
    protected int x, y;
    protected boolean dead;

    public Entity(BufferedImage img) {
        sprite = img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void move();

    @Override
    public void run() {
        while(!dead) {
            move();
        }
    }
}
