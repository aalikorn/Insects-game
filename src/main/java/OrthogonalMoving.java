import java.util.Map;


/**
 * The OrthogonalMoving interface represents moving of entities that can move orthogonally on a game board.
 */
interface OrthogonalMoving {

    /**
     * Retrieves the visible food value in the orthogonal direction from the entity's position.
     *
     * @param dir
     * @param entityPosition
     * @param boardData
     * @param boardSize
     * @return an integer value.
     */
    int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                           Map<EntityPosition, BoardEntity> boardData, int boardSize);

    /**
     * Implements orthogonal moving on the game board.
     *
     * @param dir
     * @param entityPosition
     * @param color
     * @param boardData
     * @param boardSize
     * @return an integer value
     */
    int travelOrthogonally(Direction dir, EntityPosition entityPosition,
                           InsectColor color, Map<EntityPosition, BoardEntity> boardData, int boardSize);
}