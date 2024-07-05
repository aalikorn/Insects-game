import java.util.Map;

/**
 * The Grasshopper class represents a grasshopper insect in a board game. It extends the Insect class,
 * providing specific functionality for grasshopper movement and actions.
 * @see Insect
 */
public class Grasshopper extends Insect {

    public Grasshopper(EntityPosition entityPosition, InsectColor color) {
        super(entityPosition, color);
    }
    public String getName() {
        return "Grasshopper";
    }
    /**
     * Determines the best direction for the grasshopper based on sums of food visible
     * values in each direction.
     *
     * @param boardSize
     * @param boardData
     * @return The best diagonal direction
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
     * Implements grasshopper's moving depending on the direction and
     * returns the value of food
     *
     * @param dir
     * @param boardData
     * @param boardSize
     * @return an Integer value
     */
    int travelDirection(Direction dir, Map<EntityPosition, BoardEntity> boardData, int boardSize) {
        int s = 0;
        switch (dir) {
            case E:
                for (int i = this.entityPosition.getX() + 2; i < (boardSize + 1); i += 2) {
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
                for (int i = this.entityPosition.getX() - 2; i > 0; i -= 2) {
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
                for (int i = this.entityPosition.getY() - 2; i > 0; i -= 2) {
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
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            case S:
                for (int i = this.entityPosition.getY() + 2; i < (boardSize + 1); i += 2) {
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
                Main.getBoard().addInsect(entityPosition.getX(), entityPosition.getY(), null);
                return s;
            default:
                return 0;
        }
    }
}