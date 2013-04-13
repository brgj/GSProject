package helpers;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Helper class holds the blocksize of the map and resizes entities to that size.
 */
public class Helper {

    public static double xBlockSize, yBlockSize;

    private Helper() {

    }

    /**
     * resizes the image passed in and returns a copy
     *
     * @param image
     * @return
     */
    public static BufferedImage resizeImage(final Image image) {
        final BufferedImage bufferedImage = new BufferedImage((int)xBlockSize, (int)yBlockSize,
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, (int)xBlockSize, (int)yBlockSize, null);
        graphics2D.dispose();

        return bufferedImage;
    }
}
