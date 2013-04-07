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
    private Timer t;
    private BombType bt;
    private int firePower;

    public Bomb(int fuse, int fp, int x, int y) {
        super(Thing.bomb1Img, x, y);
        firePower = fp;
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
                        t.stop();
                        t = null;
                        break;
                }
            }
        });

        t.start();
    }

    public List<Point> blowUp(int maxX, int maxY) {

        List<Point> points = new ArrayList<Point>();

        points.add(new Point(x, y));

        for(int i = 1; i <= firePower; i++) {
            if (x >= i)
                points.add(new Point(x - i, y));

            if (x <= maxX - i)
                points.add(new Point(x + i, y));

            if (y >= i)
                points.add(new Point(x, y - i));

            if (y <= maxY - i)
                points.add(new Point(x, y + i));
        }

        return points;
    }

    enum BombType {
        Bomb1,
        Bomb2,
        Bomb3
    }
}
