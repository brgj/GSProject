package image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {
    BufferedImage image;

    public ImagePanel(BufferedImage img) {
        image = img;
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
