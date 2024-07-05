import java.util.Map;

/**
 * The EntityPosition class represents the position of an entity on the game board with x and y coordinates.
 */
public class EntityPosition {
    private int x;
    private int y;

    /**
     * Constructs an EntityPosition object with specified x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public EntityPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the x-coordinate of the position.
     *
     * @return The x-coordinate of the position.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of the position.
     *
     * @return The y-coordinate of the position.
     */
    public int getY() {
        return y;
    }
}
