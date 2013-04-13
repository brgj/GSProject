package objects;

import helpers.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The Bomb class creates a bomb with a timer.
 * As the timer reaches the explosion point, it triggers the animation of the image.
 * Sets a boolean if it explodes to notify the world to take the appropriate action to clean it up.
 */
public class Bomb extends Item {
    private Timer t;
    public BombType bt;
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

    public List<Point> blowUp(int[][] map) {
        int maxX = map[0].length - 1;
        int maxY = map.length - 1;
        boolean stopLeft = false;
        boolean stopRight = false;
        boolean stopUp = false;
        boolean stopDown = false;

        List<Point> points = new ArrayList<Point>();
        points.add(new Point(x, y));

        for (int i = 1; i <= firePower; i++) {
            // Left
            if (x >= i && !stopLeft) {
                points.add(new Point(x - i, y));
                if (map[y][x - i] == 1) {
                    stopLeft = true;
                }
            }

            // Right
            if (x <= maxX - i && !stopRight) {
                points.add(new Point(x + i, y));
                if (map[y][x + i] == 1) {
                    stopRight = true;
                }
            }

            // Up
            if (y >= i && !stopUp) {
                points.add(new Point(x, y - i));
                if (map[y - i][x] == 1) {
                    stopUp = true;
                }
            }

            // Down
            if (y <= maxY - i && !stopDown) {
                points.add(new Point(x, y + i));
                if (map[y + i][x] == 1) {
                    stopDown = true;
                }
            }
        }

        return points;
    }

    enum BombType {
        Bomb1,
        Bomb2,
        Bomb3
    }
}
