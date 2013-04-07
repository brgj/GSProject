package objects;

import helpers.Helper;

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
    BombType bt;

    public Bomb(int fuse, int x, int y) {
        super(Thing.bomb1Img);
        this.x = x;
        this.y = y;
        bt = BombType.Bomb1;
        t = new Timer(fuse / 3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (bt) {
                    case Bomb1:
                        bt = BombType.Bomb2;
                        sprite = Helper.resizeImage(bomb2Img);
                        t.start();
                        break;
                    case Bomb2:
                        bt = BombType.Bomb3;
                        sprite = Helper.resizeImage(bomb3Img);
                        t.start();
                        break;
                    case Bomb3:
                        destroyed = true;
                        break;
                }
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

    enum BombType {
        Bomb1,
        Bomb2,
        Bomb3
    }
}
