package objects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/6/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Explosion extends Item {
    Timer t;

    public Explosion(int x, int y) {
        super(Thing.explosionImg);
        this.x = x;
        this.y = y;
        t = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroyed = true;
            }
        });

        t.start();
    }
}
