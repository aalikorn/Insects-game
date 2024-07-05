/**
 * The FoodPoint class represents a specific type of entity on the game board, namely a food point.
 * It extends the BoardEntity class and includes a value associated with the food point.
 * @see BoardEntity
 */
class FoodPoint extends BoardEntity {
    protected int value;

    /**
     * Constructs a FoodPoint object with a specified entity position and food value.
     *
     * @param position
     * @param value
     */
    public FoodPoint(EntityPosition position, int value) {
        this.entityPosition = position;
        this.value = value;
    }
}