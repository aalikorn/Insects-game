import java.util.Map;

/**
 * The Butterfly class represents a butterfly insect in a board game. It extends the Insect class
 * and implements the OrthogonalMoving interface for specific movement functionality.
 * @see Insect
 */
public class Butterfly extends Insect implements OrthogonalMoving {
    /**
     * Constructs a Butterfly object with a specified entity position and color.
     *
     * @param entityPosition
     * @param color
     */
    public Butterfly(EntityPosition entityPosition, InsectColor color) {
        super(entityPosition, color);
    }
    /**
     * Retrieves the string representation of entity name
     *
     * @return The name
     */
    public String getName() {
        return "Butterfly";
    }
    /**
     * Determines the best direction for the to move based on the sum of food visible values in different directions.
     *
     * @param boardData
     * @param boardSize
     * @return The best direction to move.
     */
    public Direction getBestDirection(Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        int s = Main.getBoard().getDirectionSum(this, Direction.S);
        int n = Main.getBoard().getDirectionSum(this, Direction.N);
        int e = Main.getBoard().getDirectionSum(this, Direction.E);
        int w = Main.getBoard().getDirectionSum(this, Direction.W);
        int max = Math.max(Math.max(s, Math.max(e, w)), n);
        if (max == n) {
            return Direction.N;
        } else if (max == e) {
            return Direction.E;
        } else if (max == s) {
            return Direction.S;
        } else {
            return Direction.W;
        }
    }
    /**
     * Calls function travelOrthogonally implementing orthogonal moving
     *
     * @param dir
     * @param boardData
     * @param boardSize
     * @return Integer value - the result of traveling in orthogonal direction.
     */
    int travelDirection(Direction dir, Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        return travelOrthogonally(
                dir, entityPosition, color, Main.getBoard().getBoardData(), Main.getBoard().getBoardSize());
    }
}
