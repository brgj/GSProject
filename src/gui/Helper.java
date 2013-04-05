package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/5/13
 * Time: 2:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class Helper {

    private Helper() {

    }

    /**
     * resizes the image passed in and returns a copy
     *
     * @param image
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage resizeImage(final Image image, int width,
                                            int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();

        return bufferedImage;
    }
}
