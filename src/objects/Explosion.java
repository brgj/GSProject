package objects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The explosion class is used to spawn an explosion after a bomb blows up.
 * The explosions last for 250 ms and will kill anything in the blast range.
 */
public class Explosion extends Item {
    Timer t;

    public Explosion(int x, int y) {
        super(Thing.explosionImg, x, y);
        t = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroyed = true;
                t.stop();
                t = null;
            }
        });

        t.start();
    }
}
