package objects;

import java.awt.image.BufferedImage;

/**
 * The Item class is the superclass of non-entity things on the map.
 * Has an initial x and y position, but no movement.
 */
public abstract class Item extends Thing {
    public Item(BufferedImage img, int x, int y) {
        super(img);
        this.x = x;
        this.y = y;
    }
}
