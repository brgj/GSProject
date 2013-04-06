package objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Enemy extends Entity {

    public Enemy() {
        super(Thing.enemyImg);
    }

    public int calcPath(int[][] map) {
        throw new NotImplementedException();
    }

    public void move(int direction) {
        throw new NotImplementedException();
    }
}
