/**
 * The Direction enum represents directions.
 */
public enum Direction {
    N("North"),
    E("East"),
    S("South"),
    W("West"),
    NE("North-East"),
    SE("South-East"),
    SW("South-West"),
    NW("North-West");

    private String textRepresentation;

    /**
     * Constructor for Direction enum with a specified text representation.
     *
     * @param text
     */
    private Direction(String text) {
        this.textRepresentation = text;
    }

    /**
     * Retrieves the text representation of the direction.
     *
     * @return string - the text representation of the direction.
     */
    public String getTextRepresentation() {
        return textRepresentation;
    }
}