/**
 * An interface that allows a game to be managed externally. Useful to allow it
 * to be used in an
 * arcade/anthology style game collection.
 */
public interface GameManagementInterface {
    /**
     * Returns a description of the options supported/required by the game.
     * 
     * Useful when prompting the user for game options.
     * 
     * @return the description of the options.
     */
    public String optionsDescription();

    /**
     * Initialize the game based on user-selected options (if necessary)
     * 
     * @param options the user-provided options for initialization.
     * @throws InitializationException if there is an error during initialization
     */
    public void initializeGame(String[] options) throws InitializationException;

    /**
     * Reset the game to its initial state
     */
    public void resetGame();

    /**
     * Checks if the game has been properly initialized.
     * 
     * Useful for determining if game setup procedures have been successfully
     * completed before starting the game.
     * 
     * @return true if the game is initialized, false otherwise.
     */
    public boolean isInitialized();

    /**
     * Returns a description of the actions the player can take during the game.
     * 
     * Useful when prompting the user to select their next move, explaining the
     * valid inputs or actions they can perform.
     * 
     * @return a String describing the available actions for the player.
     */
    public String actionDescription();

    /**
     * Checks if the current game has concluded.
     * 
     * Useful for determining when to end the game loop and possibly display the
     * game over message.
     * 
     * @return true if the game is over, false if the game is still ongoing.
     */
    public boolean isGameOver();

    /**
     * Checks if the current game has been won.
     * 
     * Useful for determining the outcome of the game once it has concluded.
     * 
     * @return true if the game has been won, false otherwise.
     */
    public boolean isGameWon();

    /**
     * Executes a specific action within the game, based on player input.
     * 
     * Allows the game to update its state based on the player's action. The
     * character 'c' represents the action to perform, and the array 'strings'
     * provides additional data needed for the action, if any.
     * 
     * @param c       the action command as a character.
     * @param strings additional data required for performing the action, if any.
     * @return true if the action was successfully performed, false otherwise.
     */
    public boolean performAction(char c, String[] strings);

    /**
     * Displays the current state of the game.
     * 
     * Useful for providing the player with feedback about the game, such as the
     * current score, positions, or game board.
     */
    public void printState();
}
