package objects;

/**
 * The Player class is used to define a user player.
 * Player can move and set bombs.
 */
public class Player extends Entity {

    public Player() {
        super(Thing.playerImg);
        x = 0;
        y = 0;
    }

    @Override
    public void move(int direction) {
        switch (direction) {
            // Left
            case 1:
                x--;
                return;
            // Up
            case 2:
                y--;
                return;
            // Right
            case 3:
                x++;
                return;
            // Down
            case 4:
                y++;
        }
    }
}
