package objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/5/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bomb extends Item {
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

    public List<Point> blowUp(int maxX, int maxY) {

        List<Point> points = new ArrayList<Point>();

        points.add(new Point(x, y));

        if (x != 0)
            points.add(new Point(x - 1, y));

        if (x != maxX)
            points.add(new Point(x + 1, y));

        if (y != 0)
            points.add(new Point(x, y - 1));

        if (y != maxY)
            points.add(new Point(x, y + 1));

        return points;
    }
}
