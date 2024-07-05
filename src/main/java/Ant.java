import java.util.Map;


/**
 * The Ant class represents an ant insect in a board game. It extends the Insect class
 * and implements both OrthogonalMoving and DiagonalMoving interfaces for specific movement functionality.
 * @see Insect
 */
public class Ant extends Insect implements OrthogonalMoving, DiagonalMoving {
    /**
     * Constructs an Ant object with a specified entity position and color.
     *
     * @param entityPosition
     * @param color
     */
    public Ant(EntityPosition entityPosition, InsectColor color) {
        super(entityPosition, color);
    }
    /**
     * Retrieves the string representation of entity name
     *
     * @return The name 
     */
    public String getName() {
        return "Ant";
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
        int se = Main.getBoard().getDirectionSum(this, Direction.SE);
        int ne = Main.getBoard().getDirectionSum(this, Direction.NE);
        int sw = Main.getBoard().getDirectionSum(this, Direction.SW);
        int nw = Main.getBoard().getDirectionSum(this, Direction.NW);
        int max = Math.max(Math.max(Math.max(s, Math.max(e, w)), n), Math.max(Math.max(se, Math.max(ne, sw)), nw));
        if (max == n) {
            return Direction.N;
        } else if (max == e) {
            return Direction.E;
        } else if (max == s) {
            return Direction.S;
        } else if (max == w) {
            return Direction.W;
        } else if (max == ne) {
            return Direction.NE;
        } else if (max == se) {
            return Direction.SE;
        } else if (max == sw) {
            return Direction.SW;
        } else {
            return Direction.NW;
        }
    }

    /**
     * Calls function travelOrthogonally or travelDiagonally depending on the best direction
     *
     * @param dir
     * @param boardData
     * @param boardSize
     * @return Integer value - the result of traveling in the best direction.
     */
    int travelDirection(Direction dir, Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        switch (dir) {
            case N:
            case S:
            case W:
            case E:
                return travelOrthogonally(
                        dir, entityPosition, color, Main.getBoard().getBoardData(), Main.getBoard().getBoardSize());
            case SE:
            case SW:
            case NE:
            case NW:
                return travelDiagonally(
                        dir, entityPosition, color, Main.getBoard().getBoardData(), Main.getBoard().getBoardSize());
            default:
                return 0;
        }
    }
}