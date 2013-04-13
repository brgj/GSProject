package objects;

import java.awt.image.BufferedImage;

/**
 * Thing is the superclass of all non environment objects in the game.
 * The images associated with each are defined here.
 */
public abstract class Thing {
    public static BufferedImage playerImg;
    public static BufferedImage playerDeadImg;
    public static BufferedImage enemyDeadImg;
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
