import java.util.Map;

/**
 * The Insect class is an abstract class representing insects on the game board.
 * It extends the BoardEntity class and provides methods for orthogonal and diagonal movement.
 * @see BoardEntity
 */
abstract class Insect extends BoardEntity {

    /**
     * Gets the visible value of the diagonal direction.
     *
     * @param dir
     * @param entityPosition
     * @param boardData
     * @param boardSize
     * @return an integer value
     */
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                                Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        return Main.getBoard().getDirectionSum(this, dir);
    }
    /**
     * Gets the visible value of the orthogonal direction.
     *
     * @param dir
     * @param entityPosition
     * @param boardData
     * @param boardSize
     * @return an integer value
     */
    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                                  Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        return Main.getBoard().getDirectionSum(this, dir);
    }

    /**
     * Implements orthogonal moving
     *
     * @param dir
     * @param entityPosition
     * @param color
     * @param boardData
     * @param boardSize
     * @return an Integer value
     */
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition,
                                  InsectColor color, Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        int s = 0;
        switch (dir) {
            case E:
                for (int i = this.entityPosition.getX() + 1; i < (boardSize + 1); i++) {
                    if (Main.getBoard().getFoodAmount(i, this.entityPosition.getY()) > 0) {
                        s += Main.getBoard().getFoodAmount(i, this.entityPosition.getY());
                        Main.getBoard().addFood(i, this.entityPosition.getY(), 0);
                    } else if (Main.getBoard().getInsect(i, this.entityPosition.getY()) != null) {
                        if (Main.getBoard().getInsect(i, this.entityPosition.getY()).getColor() != color) {
                            Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                }
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            case W:
                for (int i = this.entityPosition.getX() - 1; i > 0; i--) {
                    if (Main.getBoard().getFoodAmount(i, this.entityPosition.getY()) > 0) {
                        s += Main.getBoard().getFoodAmount(i, this.entityPosition.getY());
                        Main.getBoard().addFood(i, this.entityPosition.getY(), 0);
                    } else if (Main.getBoard().getInsect(i, this.entityPosition.getY()) != null) {
                        if (Main.getBoard().getInsect(i, this.entityPosition.getY()).getColor() != color) {
                            Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                }
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            case N:
                for (int i = this.entityPosition.getY() - 1; i > 0; i--) {
                    if (Main.getBoard().getFoodAmount(this.entityPosition.getX(), i) > 0) {
                        s += Main.getBoard().getFoodAmount(this.entityPosition.getX(), i);
                        Main.getBoard().addFood(this.entityPosition.getX(), i, 0);
                    } else if (Main.getBoard().getInsect(this.entityPosition.getX(), i) != null) {
                        if (Main.getBoard().getInsect(this.entityPosition.getX(), i).getColor() != color) {
                            Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                }
                Main.getBoard().addInsect(this.entityPosition.getX(), this.entityPosition.getY(), null);
                return s;
            case S:
                for (int i = this.entityPosition.getY() + 1; i < (boardSize + 1); i++) {
                    if (Main.getBoard().getFoodAmount(this.entityPosition.getX(), i) > 0) {
                        s += Main.getBoard().getFoodAmount(this.entityPosition.getX(), i);
                        Main.getBoard().addFood(this.entityPosition.getX(), i, 0);
                    } else if (Main.getBoard().getInsect(this.entityPosition.getX(), i) != null) {
                        if (Main.getBoard().getInsect(this.entityPosition.getX(), i).color != color) {
                            Main.getBoard().addInsect(this.entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                }
                Main.getBoard().addInsect(this.entityPosition.getX(), this.entityPosition.getY(), null);
                return s;
            default:
                return 0;
        }

    }

    /**
     * Implements diagonal moving
     *
     * @param dir
     * @param entityPosition
     * @param color
     * @param boardData
     * @param boardSize
     * @return an Integer value
     */
    public int travelDiagonally(Direction dir, EntityPosition entityPosition,
                                InsectColor color, Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        int s = 0;
        switch (dir) {
            case NE:
                int j = this.entityPosition.getY() - 1;
                int i = this.entityPosition.getX() + 1;
                while (i <= boardSize && j > 0) {
                    if (Main.getBoard().getFoodAmount(i, j) > 0) {
                        s += Main.getBoard().getFoodAmount(i, j);
                        Main.getBoard().addFood(i, j, 0);
                    } else if (Main.getBoard().getInsect(i, j) != null) {
                        if (Main.getBoard().getInsect(i, j).color != color) {
                            Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                    i += 1;
                    j -= 1;
                }
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            case NW:
                int j1 = this.entityPosition.getY() - 1;
                int i1 = this.entityPosition.getX() - 1;
                while (i1 > 0 && j1 > 0) {
                    if (Main.getBoard().getFoodAmount(i1, j1) > 0) {
                        s += Main.getBoard().getFoodAmount(i1, j1);
                        Main.getBoard().addFood(i1, j1, 0);
                    } else if (Main.getBoard().getInsect(i1, j1) != null) {
                        if (Main.getBoard().getInsect(i1, j1).color != color) {
                            Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                    i1 -= 1;
                    j1 -= 1;
                }
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            case SW:
                int j2 = this.entityPosition.getY() + 1;
                int i2 = this.entityPosition.getX() - 1;
                while (i2 > 0 && j2 <= boardSize) {
                    if (Main.getBoard().getFoodAmount(i2, j2) > 0) {
                        s += Main.getBoard().getFoodAmount(i2, j2);
                        Main.getBoard().addFood(i2, j2, 0);
                    } else if (Main.getBoard().getInsect(i2, j2) != null) {
                        if (Main.getBoard().getInsect(i2, j2).color != color) {
                            Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                    i2 -= 1;
                    j2 += 1;
                }
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            case SE:
                int j3 = this.entityPosition.getY() + 1;
                int i3 = this.entityPosition.getX() + 1;
                while (i3 <= boardSize && j3 <= boardSize) {
                    if (Main.getBoard().getFoodAmount(i3, j3) > 0) {
                        s += Main.getBoard().getFoodAmount(i3, j3);
                        Main.getBoard().addFood(i3, j3, 0);
                    } else if (Main.getBoard().getInsect(i3, j3) != null) {
                        if (Main.getBoard().getInsect(i3, j3).color != color) {
                            Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                            return s;
                        }
                    }
                    i3 += 1;
                    j3 += 1;
                }
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            default:
                return 0;
        }
    }

    /**
     * Abstract method to get String - the name of the entity
     *
     * @return String - name of the entity
     */
    public abstract String getName();

    protected InsectColor color;

    /**
     * Constructs an Insect object with a specified entity position and color.
     *
     * @param position The initial position of the insect on the game board.
     * @param color    The color of the insect.
     */
    public Insect(EntityPosition position, InsectColor color) {
        this.entityPosition = position;
        this.color = color;
    }

    /**
     * Retrieves the color of the insect.
     *
     * @return The color of the insect.
     */
    public InsectColor getColor() {
        return color;
    }

    /**
     * Abstract method to get best direction to move.
     *
     * @param boardData
     * @param boardSize
     * @return best direction
     */
    public abstract Direction getBestDirection(
            Map<EntityPosition, BoardEntity> boardData, int boardSize);

    /**
     * Abstract method to implement moving depending on direction
     * @param dir
     * @param boardData
     * @param boardSize
     * @return an Integer value
     */
    abstract int travelDirection(Direction dir, Map<EntityPosition, BoardEntity> boardData, int boardSize);
}