import java.util.HashMap;
import java.util.Map;

/**
 * The Board class represents the game board containing entities, insects, and food.
 */
class Board {
    private Map<EntityPosition, BoardEntity> boardData = new HashMap<>();
    private int size;
    private int[][] food;
    private Insect[][] insects;

    /**
     * Gets the amount of food at the specified position on the board.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The amount of food at the specified position.
     */
    public int getFoodAmount(int x, int y) {
        return food[x][y];
    }

    /**
     * Gets the insect at the specified position on the board.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The insect at the specified position.
     */
    public Insect getInsect(int x, int y) {
        return insects[x][y];
    }

    /**
     * Adds food to the specified position on the board.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param n The amount of food to add.
     */
    public void addFood(int x, int y, int n) {
        food[x][y] = n;
    }

    /**
     * Adds an insect to the specified position on the board.
     *
     * @param x      The x-coordinate.
     * @param y      The y-coordinate.
     * @param insect The insect to add.
     */
    public void addInsect(int x, int y, Insect insect) {
        insects[x][y] = insect;
    }

    /**
     * Constructor for creating a board with the specified size.
     *
     * @param boardSize The size of the board.
     */
    public Board(int boardSize) {
        size = boardSize;
        food = new int[boardSize + 1][boardSize + 1];
        insects = new Insect[boardSize + 1][boardSize + 1];
    }

    /**
     * Gets the size of the board.
     *
     * @return The size of the board.
     */
    public int getBoardSize() {
        return size;
    }

    /**
     * Adds an entity to the board data.
     *
     * @param entity The entity to add.
     */
    public void addEntity(BoardEntity entity) {
        boardData.put(entity.entityPosition, entity);
    }

    /**
     * Gets the entity at the specified position on the board.
     *
     * @param position The position of the entity.
     * @return The entity at the specified position.
     */
    public BoardEntity getEntity(EntityPosition position) {
        return boardData.get(position);
    }

    /**
     * Gets the best direction of movement for the given insect.
     *
     * @param insect The insect for which to determine the direction.
     * @return The direction of movement.
     */
    public Direction getDirection(Insect insect) {
        return insect.getBestDirection(boardData, size);
    }

    /**
     * Gets the sum of food in the specified direction for the given insect.
     *
     * @param insect
     * @param direction
     * @return an Integer value
     */
    public int getDirectionSum(Insect insect, Direction direction) {
        int s = 0;
        if (insect instanceof Grasshopper) {
            switch (direction) {
                case S:
                    for (int i = insect.entityPosition.getY() + 2; i <= size; i += 2) {
                        if (food[insect.entityPosition.getX()][i] > 0) {
                            s += food[insect.entityPosition.getX()][i];
                        }
                    }
                    return s;
                case N:
                    for (int i = insect.entityPosition.getY() - 2; i > 0; i -= 2) {
                        if (food[insect.entityPosition.getX()][i] > 0) {
                            s += food[insect.entityPosition.getX()][i];
                        }
                    }
                    return s;
                case W:
                    for (int i = insect.entityPosition.getX() - 2; i > 0; i -= 2) {
                        if (food[i][insect.entityPosition.getY()] > 0) {
                            s += food[i][insect.entityPosition.getY()];
                        }
                    }
                    return s;
                case E:
                    for (int i = insect.entityPosition.getX() + 2; i <= size; i += 2) {
                        if (food[i][insect.entityPosition.getY()] > 0) {
                            s += food[i][insect.entityPosition.getY()];
                        }
                    }
                    return s;
                default:
                    return 0;
            }
        }
        switch (direction) {
            case S:
                for (int i = size; i > insect.entityPosition.getY(); i--) {
                    if (food[insect.entityPosition.getX()][i] > 0) {
                        s += food[insect.entityPosition.getX()][i];
                    }
                }
                return s;
            case N:
                for (int i = 1; i < insect.entityPosition.getY(); i++) {
                    if (food[insect.entityPosition.getX()][i] > 0) {
                        s += food[insect.entityPosition.getX()][i];
                    }
                }
                return s;
            case W:
                for (int i = 1; i < insect.entityPosition.getX(); i++) {
                    if (food[i][insect.entityPosition.getY()] > 0) {
                        s += food[i][insect.entityPosition.getY()];
                    }
                }
                return s;
            case E:
                for (int i = size; i > insect.entityPosition.getX(); i--) {
                    if (food[i][insect.entityPosition.getY()] > 0) {
                        s += food[i][insect.entityPosition.getY()];
                    }
                }
                return s;
            case SW:
                int j = insect.entityPosition.getY() + 1;
                int i = insect.entityPosition.getX() - 1;
                while (i > 0 && j <= size) {
                    if (food[i][j] > 0) {
                        s += food[i][j];
                    }
                    i -= 1;
                    j += 1;
                }
                return s;
            case SE:
                int j1 = insect.entityPosition.getY() + 1;
                int i1 = insect.entityPosition.getX() + 1;
                while (i1 <= size && j1 <= size) {
                    if (food[i1][j1] > 0) {
                        s += food[i1][j1];
                    }
                    i1 += 1;
                    j1 += 1;
                }
                return s;
            case NE:
                int j2 = insect.entityPosition.getY() - 1;
                int i2 = insect.entityPosition.getX() + 1;
                while (i2 <= size && j2 > 0) {
                    if (food[i2][j2] > 0) {
                        s += food[i2][j2];
                    }
                    i2 += 1;
                    j2 -= 1;
                }
                return s;
            case NW:
                int j3 = insect.entityPosition.getY() - 1;
                int i3 = insect.entityPosition.getX() - 1;
                while (i3 > 0 && j3 > 0) {
                    if (food[i3][j3] > 0) {
                        s += food[i3][j3];
                    }
                    i3 -= 1;
                    j3 -= 1;
                }
                return s;
            default:
                return 0;
        }
    }

    /**
     * Gets the board data, which includes the mapping of positions to entities.
     *
     * @return The board data.
     */
    public Map<EntityPosition, BoardEntity> getBoardData() {
        return boardData;
    }
}