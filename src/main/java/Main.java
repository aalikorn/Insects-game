import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    private static ArrayList<InsectColor> ants = new ArrayList<>();
    private static ArrayList<InsectColor> butterflies = new ArrayList<>();
    private static ArrayList<InsectColor> spiders = new ArrayList<>();
    private static ArrayList<InsectColor> grasshoppers = new ArrayList<>();
    private static BufferedWriter writer;
    private static BufferedReader reader;
    private static ArrayList<Insect> insects = new ArrayList<>();
    private static ArrayList<EntityPosition> foodPositions = new ArrayList<>();

    /**
     * The method takes a string as input and writes it to output file
     *
     * @param string
     */
    public static void print(String string) {
        try {
            writer.write(string);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method reads a line of input from the input file and returns it as a string.
     *
     * @return a String value.
     */
    public static String input() {
        try {
            String s = reader.readLine();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Board gameBoard;

    /**
     * The method returns the gameBoard
     *
     * @return Board
     */
    public static Board getBoard() {
        return gameBoard;
    }

    /**
     * The main method initializes a BufferedWriter and BufferedReader objects for file input and
     * output, calls the inputHandling function, and then calls the game function.
     */
    public static void main(String[] args) {
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputHandling();
        game();
    }

    /**
     * The method implements game process. It iterates through a list of insects, determines their best direction to travel on a
     * game board, and prints out information about each insect's color, name, direction, and amount of food.
     */
    public static void game() {
        for (int i = 0; i < insects.size(); i++) {
            Insect insect = insects.get(i);
            Direction dir = insect.getBestDirection(gameBoard.getBoardData(), gameBoard.getBoardSize());
            int n = insect.travelDirection(dir, gameBoard.getBoardData(), gameBoard.getBoardSize());
            InsectColor color = insect.getColor();
            String colorS = null;
            String insectS = insect.getName();
            switch (color) {
                case GREEN:
                    colorS = "Green";
                    break;
                case RED:
                    colorS = "Red";
                    break;
                case BLUE:
                    colorS = "Blue";
                    break;
                case YELLOW:
                    colorS = "Yellow";
                    break;
                default:
                    colorS = null;
            }
            String direction = dir.getTextRepresentation();
            print(colorS + " " + insectS + " " + direction + " " + n + "\n");
        }
    }

    /**
     * The method handles input, creates the game board and adds insects and food to it.
     */
    public static void inputHandling() {
        int d = 0;
        int n  = 0;
        int m = 0;
        String dStr = input();
        String nStr = input();
        String mStr = input();

        try {
            d = checkSize(dStr);
        } catch (Exception e) {
            print(e.getMessage());
            System.exit(0);
        }

        gameBoard = new Board(d);

        try {
            n = checkNumOfInsects(nStr);
        } catch (Exception e) {
            print(e.getMessage());
            System.exit(0);
        }


        try {
            m = checkNumOfFood(mStr);
        } catch (Exception e) {
            print(e.getMessage());
            System.exit(0);
        }

        InsectColor color = null;
        int x = 0;
        int y = 0;

        int[][] takenPlaces = new int[d + 1][d + 1];
        for (int i = 0; i < n; i++) {
            String info = input();
            String[] inf = info.split(" ");
            String colorStr = inf[0];
            try {
                color = checkColor(colorStr);
            } catch (Exception e) {
                print(e.getMessage());
                System.exit(0);
            }

            String insectType = inf[1];

            String xStr = inf[2];
            try {
                y = checkCoord(xStr, d);
            } catch (Exception e) {
                print(e.getMessage());
                System.exit(0);
            }

            final int index3 = 3;
            String yStr = inf[index3];
            try {
                x = checkCoord(yStr, d);
            } catch (Exception e) {
                print(e.getMessage());
                System.exit(0);
            }

            try {
                Insect entity = checkInsectType(insectType, x, y, color, takenPlaces);
                gameBoard.addEntity(entity);
                takenPlaces[x][y] = 1;
                gameBoard.addInsect(x, y, entity);
            } catch (Exception e) {
                print(e.getMessage());
                System.exit(0);
            }
        }

        for (int i = 0; i < m; i++) {
            String info = input();
            String[] inf = info.split(" ");
            int foodAmount = Integer.parseInt(inf[0]);

            String xStr = inf[1];
            try {
                y = checkCoord(xStr, d);
            } catch (Exception e) {
                print(e.getMessage());
                System.exit(0);
            }

            String yStr = inf[2];
            try {
                x = checkCoord(yStr, d);
            } catch (Exception e) {
                print(e.getMessage());
                System.exit(0);
            }

            try {
                gameBoard.addEntity(ifFree(foodAmount, x, y, takenPlaces));
                foodPositions.add(new EntityPosition(x, y));
                takenPlaces[x][y] = 1;
                gameBoard.addFood(x, y, foodAmount);
            } catch (Exception e) {
                print(e.getMessage());
                System.exit(0);
            }

        }
    }

    /**
     * The method checks if a specific position on a grid is free and returns a FoodPoint object if
     * it is, otherwise it throws an exception.
     *
     * @param n
     * @param x
     * @param y
     * @param takenPlaces
     * @return a FoodPoint object.
     */
    public static FoodPoint ifFree(int n, int x, int y, int[][] takenPlaces) throws Exception {
        if (takenPlaces[x][y] == 1) {
            throw new TwoEntitiesOnSamePositionException();
        } else {
            return new FoodPoint(new EntityPosition(x, y), n);
        }
    }

    /**
     * The method takes in parameters for insect type, position, color, and a 2D
     * array of taken places, and returns an instance of the corresponding insect type if it is not a
     * duplicate and the position is not already taken.
     *
     * @param insectType
     * @param x
     * @param y
     * @param color
     * @param takenPlaces
     * @return instance of the Insect class
     */
    public static Insect checkInsectType(String insectType, int x, int y,
                                         InsectColor color, int[][] takenPlaces) throws Exception {
        if (takenPlaces[x][y] == 1) {
            throw new TwoEntitiesOnSamePositionException();
        }

        switch (insectType) {
            case "Ant":
                if (!ants.contains(color)) {
                    Ant ant = new Ant(new EntityPosition(x, y), color);
                    insects.add(ant);
                    ants.add(color);
                    return ant;
                } else {
                    throw new DuplicateInsectException();
                }
            case "Butterfly":
                if (!butterflies.contains(color)) {
                    butterflies.add(color);
                    Butterfly butterfly = new Butterfly(new EntityPosition(x, y), color);
                    insects.add(butterfly);
                    return butterfly;
                } else {
                    throw new DuplicateInsectException();
                }
            case "Grasshopper":
                if (!grasshoppers.contains(color)) {
                    grasshoppers.add(color);
                    Grasshopper grasshopper = new Grasshopper(new EntityPosition(x, y), color);
                    insects.add(grasshopper);
                    return grasshopper;
                } else {
                    throw new DuplicateInsectException();
                }

            case "Spider":
                if (!spiders.contains(color)) {
                    spiders.add(color);
                    Spider spider = new Spider(new EntityPosition(x, y), color);
                    insects.add(spider);
                    return spider;
                } else {
                    throw new DuplicateInsectException();
                }
            default:
                throw new InvalidInsectTypeException();
        }
    }

    /**
     * The method checks if a given coordinate is within a specified range and throws an exception if
     * it is not.
     *
     * @param coordStr
     * @param d
     * @return an integer value.
     */
    public static int checkCoord(String coordStr, int d) throws Exception {
        int coord = checkIfInt(coordStr);
        if (!((coord >= 1) && (coord <= d))) {
            throw new InvalidEntityPositionException();
        } else {
            return coord;
        }
    }

    /**
     * The method takes a string parameter representing a color and returns the
     * corresponding InsectColor enum value, or throws an exception if the color is invalid.
     *
     * @param color
     * @return InsectColor enum value.
     */
    public static InsectColor checkColor(String color) throws Exception {
        switch (color) {
            case "Red":
                return InsectColor.RED;
            case "Green":
                return InsectColor.GREEN;
            case "Blue":
                return InsectColor.BLUE;
            case "Yellow":
                return InsectColor.YELLOW;
            default:
                throw new InvalidInsectColorException();
        }
    }

    /**
     * The function checks if a given string is integer and returns the integer
     * value if yes, otherwise returns -1.
     *
     * @param str
     * @return an integer value.
     */
    public static int checkIfInt(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return -1;
            }
        }
        return Integer.parseInt(str);
    }

    /**
     * The method checks if a given integer falls within a valid range and throws an exception if
     * it does not.
     *
     * @param dStr
     * @return an integer value.
     */
    public static int checkSize(String dStr) throws Exception {
        int d = checkIfInt(dStr);
        final int upperbound = 1000;
        final int lowerbound = 4;
        if (!((d >= lowerbound) && (d <= upperbound))) {
            throw new InvalidBoardSizeException();
        } else {
            return d;
        }
    }

    /**
     * The method checks if the given number of insects is within a valid range and throws an
     * exception if it is not.
     *
     * @param nStr
     * @return an integer value.
     */
    public static int checkNumOfInsects(String nStr) throws Exception {
        int n = checkIfInt(nStr);
        final int upperbound = 16;
        if (!((n >= 1) && (n <= upperbound))) {
            throw new InvalidNumberOfInsectsException();
        } else {
            return n;
        }
    }

    /**
     * The method checks if the given number of insects is within a valid range and throws an
     * exception if it is not.
     *
     * @param mStr
     * @return an integer value.
     */
    public static int checkNumOfFood(String mStr) throws Exception {
        final int upperbound = 200;
        int m = checkIfInt(mStr);
        if (!((m >= 1) && (m <= upperbound))) {
            throw new InvalidNumberOfFoodPointsException();
        } else {
            return m;
        }
    }
}



























