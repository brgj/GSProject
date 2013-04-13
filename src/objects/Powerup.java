package objects;

/**
 * Powerups are player pickups to increase the blast range of bombs or increase the number of bombs held.
 * Random number is used to select which powerup will be used.
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
