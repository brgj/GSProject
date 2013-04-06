package objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/5/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bomb extends Item{
    Timer t;
    public Bomb(int fuse, int x, int y) {
        super(Thing.bombImg);
        this.x = x;
        this.y = y;
        t = new Timer(fuse, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroyed = true;
            }
        });

        t.start();
    }

    public Point[] blowUp() {
        return new Point[]{new Point(x, y), new Point(x+1, y),
        new Point(x-1, y), new Point(x, y+1), new Point(x, y-1)};
    }
}
