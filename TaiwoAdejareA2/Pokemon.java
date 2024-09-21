import java.util.ArrayList;

public class Pokemon {
    public static int currentId = 0;
    private final int INIT_ID_AT = 1000;

    private String name;
    private int level;
    private int ID;
    private ArrayList<String> types;
    private ArrayList<String> moves;

    /**
     * Initializes a new Pokemon object with a name, level, types, and moves.
     * Validates that moves are not empty.
     * Input: name, level, types, and moves from the constructor parameters.
     * Output: Creates a new Pokemon object with initialized values.
     * Parameters accepted: String name, int level, ArrayList<String> types,
     * ArrayList<String> moves.
     * Value returned: None (constructor).
     *
     */
    public Pokemon(String name, int level, ArrayList<String> types, ArrayList<String> moves) {
        // if moves is empty throw error
        if (moves.isEmpty()) {
            throw new IllegalStateException();
        } else {
            this.moves = moves;
        }
        this.name = name;
        this.level = level;
        this.types = types;
        this.ID = currentId + INIT_ID_AT;
        currentId++;
    }

    /**
     * Returns the Pokemon's name.
     * Input: None.
     * Output: Pokemon's name to the caller.
     * Parameters accepted: None.
     * Value returned: The name of the Pokemon (String).
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Pokemon's level.
     * Input: None.
     * Output: Pokemon's level to the caller.
     * Parameters accepted: None.
     * Value returned: The level of the Pokemon (int).
     *
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the Pokemon's ID.
     * Input: None.
     * Output: Pokemon's ID to the caller.
     * Parameters accepted: None.
     * Value returned: The ID of the Pokemon (int).
     *
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the Pokemon's types to ensure encapsulation.
     * Input: None.
     * Output: An ArrayList containing the types of the Pokemon.
     * Parameters accepted: None.
     * Value returned: The ArrayList<String> representing the Pokemon's types.
     *
     */
    public ArrayList<String> getTypes() {
        return types;
    }

    /**
     * Returns a copy of the Pokemon's moves to ensure encapsulation.
     * Input: None.
     * Output: A new ArrayList containing the moves of the Pokemon.
     * Parameters accepted: None.
     * Value returned: A copy of the ArrayList<String> representing the Pokemon's
     * moves.
     *
     */
    public ArrayList<String> getMoves() {
        return new ArrayList<>(moves);
    }

    /**
     * Generates and returns a string representation of the Pokemon, including its
     * name, ID, level, types, and moves.
     * Input: None.
     * Output: A formatted string containing the Pokemon's details to the caller.
     * Parameters accepted: None.
     * Value returned: A formatted String representing the Pokemon's details.
     *
     */
    public String toString() {
        String pokemonStr = name + "\nID: " + ID + "\nLevel: " + level + "\nType(s): " + String.join(", ", types)
                + "\nMoves: " + String.join(", ", moves);
        return pokemonStr;
    }
}
