package objects;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/5/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Thing {
    public static BufferedImage playerImg;
    public static BufferedImage deadImg;
    public static BufferedImage enemyImg;
    public static BufferedImage bomb1Img;
    public static BufferedImage bomb2Img;
    public static BufferedImage bomb3Img;
    public static BufferedImage explosionImg;
    public static BufferedImage bombUpImg;
    public static BufferedImage fireUpImg;

    public BufferedImage sprite;
    protected int x, y;
    protected boolean destroyed;

    public Thing(BufferedImage img) {
        sprite = img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
