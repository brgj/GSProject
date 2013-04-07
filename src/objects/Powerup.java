package objects;

/**
 * Created with IntelliJ IDEA.
 * User: Brad
 * Date: 4/7/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Powerup extends Item {
    public enum Power {
        FireUp,
        BombUp
    }

    Power power;

    public Powerup(int c, int x, int y) {
        super(c == Power.FireUp.ordinal() ? Thing.fireUpImg : Thing.bombUpImg, x, y);
        power = c == Power.FireUp.ordinal() ? Power.FireUp : Power.BombUp;
    }

    public Power getPower() {
        destroyed = true;
        return power;
    }
}
