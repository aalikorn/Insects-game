import java.util.Map;

/**
 * The DiagonalMoving interface represents moving of entities that can move diagonally on a game board.
 */
interface DiagonalMoving {
    /**
     * Retrieves the visible food value in the diagonal direction from the entity's position.
     *
     * @param dir
     * @param entityPosition
     * @param boardData
     * @param boardSize
     * @return an integer value.
     */
    int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                         Map<EntityPosition, BoardEntity> boardData, int boardSize);

    /**
     * Implements diagonal moving on the game board.
     *
     * @param dir
     * @param entityPosition
     * @param color
     * @param boardData
     * @param boardSize
     * @return an integer value.
     */
    int travelDiagonally(Direction dir, EntityPosition entityPosition,
                         InsectColor color, Map<EntityPosition, BoardEntity> boardData, int boardSize);
}
