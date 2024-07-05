import java.util.Map;

public class Spider extends Insect implements DiagonalMoving {

    /**
     * Constructor for creating a Spider object.
     *
     * @param entityPosition
     * @param color
     */
    public Spider(EntityPosition entityPosition, InsectColor color) {
        super(entityPosition, color);
    }

    /**
     * Gets the name of the spider.
     *
     * @return The name.
     */
    public String getName() {
        return "Spider";
    }

    /**
     * Determines the best diagonal direction for the spider based on sums of food visible
     * values in each direction.
     *
     * @param dir
     * @param boardData
     * @return The best diagonal direction
     */
    public Direction getBestDirection(Map<EntityPosition, BoardEntity> dir, int boardData) {
        int se = Main.getBoard().getDirectionSum(this, Direction.SE);
        int ne = Main.getBoard().getDirectionSum(this, Direction.NE);
        int sw = Main.getBoard().getDirectionSum(this, Direction.SW);
        int nw = Main.getBoard().getDirectionSum(this, Direction.NW);
        int max = Math.max(Math.max(se, Math.max(ne, sw)), nw);

        if (max == ne) {
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
     * Initiates the diagonal movement of the spider in the specified direction.
     *
     * @param dir
     * @param boardData
     * @param boardSize
     * @return an integer value
     */
    int travelDirection(Direction dir, Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        return travelDiagonally(dir, entityPosition, color,
                Main.getBoard().getBoardData(), Main.getBoard().getBoardSize());
    }

}
