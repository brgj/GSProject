package gui;

import image.ImagePanel;

import javax.swing.*;
import java.awt.*;
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
                {0xFF << 8, 0, 0, 0, 0},
                {0xFF << 8, 0xFF << 8, 0, 0, 0},
                {0xFF << 8, 0xFF << 8, 0xFF << 8, 0, 0},
                {0xFF << 8, 0xFF << 8, 0xFF << 8, 0xFF << 8, 0},
                {0xFF << 8, 0xFF << 8, 0xFF << 8, 0xFF << 8, 0xFF << 8}
        };

        BufferedImage img = new BufferedImage(map.length * 100, map[0].length * 100, BufferedImage.TYPE_INT_RGB);

        RGB = new int[img.getHeight() * img.getWidth()];

        for(int y = 0; y < img.getHeight(); y++) {
            for(int x = 0; x < img.getWidth(); x++) {
                double yRatio = (double) y / img.getHeight();
                double xRatio = (double) x / img.getWidth();

                RGB[y * img.getWidth() + x] = map[(int)(map.length * yRatio)][(int)(map[0].length * xRatio)];
            }
        }

        img.setRGB(0, 0, img.getWidth(), img.getHeight(), RGB, 0, img.getWidth());

        image = new ImagePanel(img);

        add(image);

        pack();
    }
}
