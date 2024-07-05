/**
 * The base Exception class for custom exceptions.
 */
public class Exception extends java.lang.Exception {
    public String getMessage() {
        return null;
    }
}

/**
 * Exception indicating an invalid board size.
 * @see Exception
 */
class InvalidBoardSizeException extends Exception {
    public String getMessage() {
        return "Invalid board size";
    }
}

/**
 * Exception indicating an invalid number of insects on the board.
 * @see Exception
 */
class InvalidNumberOfInsectsException extends Exception {
    public String getMessage() {
        return "Invalid number of insects";
    }
}

/**
 * Exception indicating an invalid number of food points.
 * @see Exception
 */
class InvalidNumberOfFoodPointsException extends Exception {
    public String getMessage() {
        return "Invalid number of food points";
    }
}

/**
 * Exception indicating an invalid insect color.
 * @see Exception
 */
class InvalidInsectColorException extends Exception {
    public String getMessage() {
        return "Invalid insect color";
    }
}

/**
 * Exception indicating an invalid insect type.
 * @see Exception
 */
class InvalidInsectTypeException extends Exception {
    public String getMessage() {
        return "Invalid insect type";
    }
}

/**
 * Exception indicating an invalid entity position on the board.
 * @see Exception
 */
class InvalidEntityPositionException extends Exception {
    public String getMessage() {
        return "Invalid entity position";
    }
}

/**
 * Exception indicating duplicate insects on the board.
 * @see Exception
 */
class DuplicateInsectException extends Exception {
    public String getMessage() {
        return "Duplicate insects";
    }
}

/**
 * Exception indicating two entities in the same position on the board.
 * @see Exception
 */
class TwoEntitiesOnSamePositionException extends Exception {
    public String getMessage() {
        return "Two entities in the same position";
    }
}