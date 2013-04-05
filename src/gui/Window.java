package gui;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Window extends JFrame {
    int[][] map;
    ImagePanel image;

    public Window() {
        initComponents();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initComponents() {
        int[] RGB;

        map = new int[][]{
                {1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0}
        };

        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

        RGB = new int[img.getHeight() * img.getWidth()];

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                double yRatio = (double) y / img.getHeight();
                double xRatio = (double) x / img.getWidth();

                RGB[y * img.getWidth() + x] = getColour(map[(int) (map.length * yRatio)][(int) (map[0].length * xRatio)]);
            }
        }

        img.setRGB(0, 0, img.getWidth(), img.getHeight(), RGB, 0, img.getWidth());

        image = new ImagePanel(img);

        add(image);

        pack();
    }

    public int getColour(int index) {
        switch(index) {
            // White
            case 0:
                return 0xFFFFFF;
            // Black
            case 1:
                return 0;
            // Red
            case 2:
                return 0xFF0000;
            // Green
            case 3:
                return 0x00FF00;
            // Blue
            case 4:
                return 0x0000FF;
            // Stupid colour
            case 5:
                return 0x5EDA9E;
            default:
                return 0;
        }
    }
}
