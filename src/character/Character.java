package character;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Character {
    public BufferedImage sprite;
    private int x, y;

    public Character(BufferedImage img) {
        sprite = img;
    }
}
