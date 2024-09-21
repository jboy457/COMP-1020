/**
 * COMP 1020 SECTION [A03]
 * INSTRUCTOR: [Skyla Dudek]
 * NAME: [Adejare Taiwo]
 *
 * PURPOSE: [Implements RPS and Hoarder Game]
 */
import java.util.Scanner;

public class GameCollection {
    static Scanner scnr = new Scanner(System.in);

    // Commands
    final static String[] QUIT_CMD = { "q", "x" };
    final static String YES_CMD = "y";
    final static String RPS_CMD = "r";
    final static String HOARD_CMD = "h";

    final static String R_LABEL = "Rock-Paper-Scissors";
    final static String H_LABEL = "Hoarder";

    // Game Collection Status
    static int wins = 0;
    static int losses = 0;

    public static void main(String[] args) {

        String userCmd = getUserCmd();

        // Keep game on until player quit 
        while (!isQuit(userCmd)) {
            switch (userCmd.trim()) {
                case RPS_CMD:
                    playRPS();
                    break;
                case HOARD_CMD:
                    playHoarder();
                    break;
                default:
                    System.out.println("Invalid command input, Try again.");
                    break;
            }

            userCmd = getUserCmd();
        }

        scnr.close();
        System.out.println("Successfully terminated program!!!");
    }

    /**
     * Determines if a given command is a quit command.
     * Input: A String representing a command.
     * Output: true if the command is a quit command, false otherwise.
     * Parameters accepted: One String parameter (cmd) - the command to check.
     * Value returned: boolean - true if the command matches any of the quit
     * commands, false if it does not.
     */
    private static boolean isQuit(String cmd) {
        boolean quit = false;
        for (int i = 0; i < QUIT_CMD.length && !quit; i++) {
            if (QUIT_CMD[i].equals(cmd))
                quit = true;
        }
        return quit;
    }

    /**
     * Prompts the user for a command to select a game or quit.
     * Input: None.
     * Output: The user's command as a String.
     * Parameters accepted: None.
     * Value returned: The command entered by the user (String) - either a game
     * selection command or 'q' to quit.
     */
    private static String getUserCmd() {
        System.out.print("\nWhat would you like to play? '" + HOARD_CMD + "' for " + H_LABEL + ", '" + RPS_CMD
                + "' for " + R_LABEL + " or 'q' to quit: ");
        return scnr.nextLine();
    }

    /**
     * Manages the gameplay for Rock, Paper, Scissors (RPS).
     * Input: None.
     * Output: Executes the game of Rock, Paper, Scissors, displaying game states
     * and outcomes.
     * Parameters accepted: None.
     * Value returned: None (void).
     */
    private static void playRPS() {
        // Flag check to end game and create RPS instance
        boolean endGame = false;
        GameManagementInterface initRPS = new RPS();

        // Prompt use to enter game option
        String numOfWin = getOption(initRPS.optionsDescription());
        System.out.println();

        // Convert game option into array.
        String[] numOfWinArr = { numOfWin };
        try {
            // Initalize game and print state
            initRPS.initializeGame(numOfWinArr);
            initRPS.printState();

            // Game loop runs until the game is over or the user decides to quit.
            while (!initRPS.isGameOver() && !endGame) {
                String nextAction = getAction(initRPS.actionDescription());
                if (isQuit(nextAction)) {
                    endGame = quiteGame(initRPS);
                } else if (nextAction.equals(RPS_CMD)) {
                    initRPS.resetGame();
                    System.out.println("Restarting rock, paper, scissors.");
                } else {
                    if (nextAction.length() == 1) {
                        initRPS.performAction(nextAction.charAt(0), null);
                        initRPS.printState();
                    } else {
                        System.out.println("Invalid action perfomed. Try again.");
                    }

                }
            }

            // Display game over message and update win/loss records.
            if (endGame) {
                System.out.println("Game exited.");
            } else {
                System.out.printf("\nGame Over: %s, Game won: %s\n", initRPS.isGameOver(), initRPS.isGameWon());
                if (initRPS.isGameWon()) {
                    wins++;
                } else {
                    losses++;
                }
                System.out.printf("So far you have %d win(s) and %d loss(es)!\n", wins, losses);
            }
        } catch (InitializationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prompts the user for their next action based on the given options.
     * Input: A String representing the available actions.
     * Output: Captures and returns the user's action as a String.
     * Parameters accepted: One String parameter (actions) - the actions available
     * for the user to choose from.
     * Value returned: The user's chosen action (String).
     */
    private static String getAction(String actions) {
        System.out.printf("\nWhat's your next action [%s]: ", actions);
        return scnr.nextLine();
    }

    /**
     * Prompts the user to enter a selection from provided game options.
     * Parameters: options - A string of available game options.
     * Returns: User's input as a selection among the options.
     */
    public static String getOption(String options) {
        System.out.printf("Enter the game options (%s): ", options);
        return scnr.nextLine();
    }

    /**
     * Asks the user if they want to exit the game and returns their decision.
     * Parameters: game - The game interface currently being managed.
     * Returns: true if the user decides to exit the game, false otherwise.
     */
    private static boolean quiteGame(GameManagementInterface game) {
        // Prompt the user with a question to confirm if they want to exit the game.
        boolean quiteGame = false;
        System.out.print("Do you want to exit the game (y/n)? ");
        String res = scnr.nextLine();

        if (res.equals(YES_CMD)) {
            quiteGame = true;
        }

        return quiteGame;
    }

    /**
     * Manages the gameplay for the Hoarder game.
     * Initiates and handles game progression, including game initialization, player
     * actions, and game termination.
     * Utilizes user input to navigate through game options, actions, and decisions
     * to continue or quit the game.
     * Upon game completion or exit, displays the game outcome and updates the
     * win/loss record.
     */
    private static void playHoarder() {
        boolean endGame = false;
        GameManagementInterface initHoarder = new Hoarders();

        String boardData = getOption(initHoarder.optionsDescription());
        String[] options = { boardData };
        try {
            initHoarder.initializeGame(options);
            initHoarder.printState();

             // Game loop: continues until the game ends or the player decides to quit.
            while (!initHoarder.isGameOver() && !endGame) {
                String nextAction = getAction(initHoarder.actionDescription());
                if (isQuit(nextAction)) {
                    endGame = quiteGame(initHoarder);
                } else if (nextAction.equals(RPS_CMD)) { // RPS command also having the same as restart.
                    initHoarder.resetGame();
                    System.out.println("Restarting Hoarder game.");
                } else {
                    if (validateHoardAction(nextAction)) {
                        char action = nextAction.charAt(0);
                        String[] cell = nextAction.substring(2, nextAction.length()).split(" ");
                        initHoarder.performAction(action, cell);
                        initHoarder.printState();
                    } else {
                        System.out.println("Invalid action format inputed.");
                    }
                }
            }
             // Conclude the game with a message and update the win/loss record.
            if (endGame) {
                System.out.println("Game exited.");
            } else {
                System.out.printf("\nGame Over: %s, Game won: %s\n", initHoarder.isGameOver(), initHoarder.isGameWon());
                if (initHoarder.isGameWon()) {
                    wins++;
                } else {
                    losses++;
                }
                System.out.printf("So far you have %d win(s) and %d loss(es)!\n", wins, losses);
            }
        } catch (InitializationException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Validates the format of a player's action input for the Hoarder game.
     * Checks if the action string adheres to a specific format: an alphabetic
     * character followed by space-separated digits.
     * The input is considered valid if it starts with an alphabetic character and
     * is followed by a pattern of digit and space.
     *
     * Parameters: action - A string representing the player's action.
     * Returns: true if the action's format is valid, false otherwise.
     */
    private static boolean validateHoardAction(String action) {
        boolean actionValid = true;
        boolean movesValid = true;
        boolean spaceValid = true;

        // Check if the length is greater than expected length for a valid action.
        if (action.length() > 4) {
            actionValid = Character.isAlphabetic(action.charAt(0));
               // Loop through the string to check alternating digits and spaces after the initial character.
            for (int i = 1; i < action.length() && actionValid && spaceValid && movesValid; i++) {
                if (i % 2 == 0) {
                    movesValid = Character.isDigit(action.charAt(i));
                } else {
                    spaceValid = Character.isWhitespace(action.charAt(i));
                }
            }
        } else {
             // If the action string is too short, automatically mark it as invalid.
            actionValid = false;
            movesValid = false;
        }

        return actionValid && movesValid && spaceValid;
    }
}
