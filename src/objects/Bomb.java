package objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/5/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bomb extends Item{
    public Bomb(int fuse, int x, int y) {
        super(Thing.bombImg);
    }

    public Point[] blowUp() {
        throw new NotImplementedException();
    }
}
