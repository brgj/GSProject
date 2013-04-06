package core;

import helpers.Delegate;
import helpers.Helper;
import objects.Enemy;
import objects.Item;
import objects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/5/13
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class World implements Runnable{
    private int[][] map;
    private Delegate delegate;
    private Player player;
    private List<Enemy> enemies;
    private List<Item> items;

    public boolean exists;

    public World(int[][] m, Delegate d) {
        map = m;
        delegate = d;
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();
        exists = true;
        setPlayer();
    }

    public Player getPlayer() {
        return player;
    }

    // Resizes sprites to block size and sets player
    public void setPlayer() {
        player = new Player();
        player.sprite = Helper.resizeImage(player.sprite);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    // Resizes sprite to block size and adds enemy to list
    public void addEnemy(Enemy e) {
        e.sprite = Helper.resizeImage(e.sprite);
        enemies.add(e);
    }

    public List<Item> getItems() {
        return items;
    }

    // Resizes sprite to block size and adds item to list
    public void addItem(Item i) {
        i.sprite = Helper.resizeImage(i.sprite);
        items.add(i);
    }

    public void playerAction(int action, int[][] activeMap) {
        int y = player.getY();
        int x = player.getX();

        if(activeMap[y][x] == -2) {
            player.kill();
            exists = false;
            return;
        }

        switch(action) {
            // Left
            case 1:
                if(activeMap[y][x-1] == 0)
                    player.move(1);
                break;
            // Up
            case 2:
                if(activeMap[y-1][x] == 0)
                    player.move(2);
                break;
            // Right
            case 3:
                if(activeMap[y][x+1] == 0)
                    player.move(3);
                break;
            // Down
            case 4:
                if(activeMap[y+1][x] == 0)
                    player.move(4);
                break;
            // Bomb
            case 5:
                player.setBomb();
                break;
        }
    }

    public int[][] getActiveMap() {
        int[][] activeMap = new int[map.length][map[0].length];

        System.arraycopy(map, 0, activeMap, 0, map.length);

        for(Enemy e : enemies) {
            activeMap[e.getY()][e.getX()] = -2;
        }

        return activeMap;
    }

    public void step() {
        int[][] activeMap = getActiveMap();

        playerAction(delegate.getInput(), activeMap);

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if(e.isDestroyed()) {
                enemies.remove(i);
                i--;
                continue;
            }
            e.move(e.calcPath(activeMap));
        }

        delegate.repaint();
    }

    @Override
    public void run() {
        while(exists) {
            try {
                Thread.sleep(35);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            step();
        }
    }
}
