package gui;

import core.World;
import helpers.Delegate;
import helpers.Helper;
import objects.Enemy;
import objects.Item;
import objects.Player;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {
    private BufferedImage image;
    World world;
    Thread wThread;
    Stack<Integer> keyStack;

    public ImagePanel(int[][] map, BufferedImage img) {
        image = img;
        keyStack = new Stack<Integer>();

        Helper.xBlockSize = (double) 500 / map[0].length;
        Helper.yBlockSize = (double) 500 / map.length;

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

        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        setFocusable(true);
        requestFocusInWindow();
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
                    }
                }
                return 0;
            }

            public void repaint() {
                ImagePanel.this.repaint();
//                wetFart();
            }
        });
        wThread = new Thread(world);

        wThread.start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);

        for(Item i : world.getItems()) {
            g.drawImage(i.sprite, (int)(i.getX() * Helper.xBlockSize + 0.5), (int)(i.getY() * Helper.yBlockSize + 0.5), null);
        }

        for(Enemy e : world.getEnemies()) {
            g.drawImage(e.sprite, (int)(e.getX() * Helper.xBlockSize + 0.5), (int)(e.getY() * Helper.yBlockSize + 0.5), null);
        }

        Player p = world.getPlayer();
        g.drawImage(p.sprite, (int)(p.getX() * Helper.xBlockSize + 0.5), (int)(p.getY() * Helper.yBlockSize + 0.5), null);
    }
}
