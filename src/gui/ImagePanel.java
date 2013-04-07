package gui;

import core.World;
import helpers.Delegate;
import helpers.Helper;
import objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private BufferedImage image;
    private World world;
    private Thread wThread;
    private Stack<Integer> keyStack;
    private int groundColour, wallColour;

    public ImagePanel() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocusInWindow();
    }

    public ImagePanel(int[][] map, int groundClr, int wallClr) {
        this();

        groundColour = groundClr;
        wallColour = wallClr;

        initComponents(map);
    }

    private void initComponents(int[][] map) {
        keyStack = new Stack<Integer>();

        Helper.xBlockSize = (double) WIDTH / map[0].length;
        Helper.yBlockSize = (double) HEIGHT / map.length;

        setBackgroundImage(map);

        createWorld(map);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyStack.push(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void createWorld(int[][] map) {
        world = new World(map, new Delegate() {
            @Override
            public int getInput() {
                if (!keyStack.empty()) {
                    switch (keyStack.pop()) {
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:
                            return 1;
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                            return 2;
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:
                            return 3;
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S:
                            return 4;
                        case KeyEvent.VK_SPACE:
                            return 5;
                    }
                }
                return 0;
            }

            @Override
            public void repaint() {
                ImagePanel.this.repaint();
            }

            @Override
            public void setBackgroundImage(int[][] map) {
                ImagePanel.this.setBackgroundImage(map);
            }
        });
        wThread = new Thread(world);

        wThread.start();
    }

    public void setBackgroundImage(int[][] map) {
        int[] RGB = new int[HEIGHT * WIDTH];

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                double yRatio = (double) y / HEIGHT;
                double xRatio = (double) x / WIDTH;

                RGB[y * WIDTH + x] = getColour(map[(int) (map.length * yRatio)][(int) (map[0].length * xRatio)]);
            }
        }

        image.setRGB(0, 0, WIDTH, HEIGHT, RGB, 0, WIDTH);
    }

    public int getColour(int index) {
        switch (index) {
            // Ground
            case 0:
                return groundColour;
            // Wall
            case 1:
                return wallColour;
            // Bomb colour same as ground colour
            case 6:
                return groundColour;
            default:
                return wallColour;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);

        if (world == null)
            return;

        for(Powerup pow : world.getPowerups()) {
            g.drawImage(pow.sprite, (int) (pow.getX() * Helper.xBlockSize + 0.5), (int) (pow.getY() * Helper.yBlockSize + 0.5), null);
        }

        Player p = world.getPlayer();
        g.drawImage(p.sprite, (int) (p.getX() * Helper.xBlockSize + 0.5), (int) (p.getY() * Helper.yBlockSize + 0.5), null);

        for (Enemy e : world.getEnemies()) {
            g.drawImage(e.sprite, (int) (e.getX() * Helper.xBlockSize + 0.5), (int) (e.getY() * Helper.yBlockSize + 0.5), null);
        }

        for (Bomb b : world.getBombs()) {
            g.drawImage(b.sprite, (int) (b.getX() * Helper.xBlockSize + 0.5), (int) (b.getY() * Helper.yBlockSize + 0.5), null);
        }

        for(Explosion e : world.getExplosions()) {
            g.drawImage(e.sprite, (int) (e.getX() * Helper.xBlockSize + 0.5), (int) (e.getY() * Helper.yBlockSize + 0.5), null);
        }

    }
}
