package objects;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/5/13
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Item extends Thing {
    public Item(BufferedImage img, int x, int y) {
        super(img);
        this.x = x;
        this.y = y;
    }
}
