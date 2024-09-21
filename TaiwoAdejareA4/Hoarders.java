import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Hoarders implements GameManagementInterface {
    // Game constants
    private final static char PEEK = 'p';
    private final static char MOVE = 'm';
    private final static char SPECIAL = 's';

    ItemPile[][] board;
    String boardFile;
    int rows;
    int columns;
    int moves;
    int specialMoves;
    int playerSMoves;
    int playerMoves;

    boolean gameOver;
    boolean playerWon;

    public Hoarders() {
        rows = 0;
        columns = 0;
        moves = 0;
        playerMoves = 0;
        playerSMoves = 0;
        specialMoves = 0;
        gameOver = false;
        playerWon = false;
    }

    @Override
    public String optionsDescription() {
        return "[board_filepath]";
    }

    @Override
    public void initializeGame(String[] options) throws InitializationException {
        if (options.length < 1) {
            throw new InitializationException(
                    "Invalid options for new match of Hoarder! Need: " + optionsDescription());
        } else {
            try {
                File file = new File(options[0]);
                Scanner scnr = new Scanner(file);
                boardFile = options[0];
                ArrayList<String> lines = new ArrayList<String>();
                while (scnr.hasNextLine()) {
                    lines.add(scnr.nextLine());
                }

                String[] boardDimesion = lines.get(0).trim().split(" ");
                rows = Integer.parseInt(boardDimesion[0]);
                columns = Integer.parseInt(boardDimesion[1]);
                board = new LinkedItemPile[rows][columns];

                ItemPile[] itemPiles = new LinkedItemPile[rows * columns];

                for (int i = 1; i < lines.size() - 2; i++) {
                    ItemPile ll = new LinkedItemPile();
                    String[] items = lines.get(i).trim().split(" ");
                    for (String item : items) { // still need to handle if its empty
                        ll.addToPile(Integer.parseInt(item));
                    }
                    itemPiles[i - 1] = ll;
                }

                moves = Integer.parseInt(lines.get(lines.size() - 2));
                specialMoves = Integer.parseInt(lines.get(lines.size() - 1));

                loadBoard(itemPiles);

                scnr.close();
            } catch (Exception e) {
                throw new InitializationException("Error getting " + optionsDescription() + ": " + e.getMessage());
            }
        }
    }

    private void loadBoard(ItemPile[] itemPiles) {
        int plieCounter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = itemPiles[plieCounter];
                plieCounter++;
            }
        }
    }

    @Override
    public void resetGame() {
        gameOver = false;
        playerWon = false;
        playerMoves = 0;
        playerSMoves = 0;

        try {
            String[] options = { boardFile };
            initializeGame(options);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isInitialized() {
        return board != null;
    }

    @Override
    public String actionDescription() {
        return "move (m), super move (s), or peek (p)";
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean isGameWon() {
        return playerWon;
    }

    /**
     * Checks if a given cell is within the bounds of the board.
     * No parameters.
     * Returns true if the cell's row and column are within the board's limits.
     */
    private boolean isCellWithinBoard(int row, int column) {
        return row <= rows && column <= columns;
    }

    /**
     * Determines if a move is strictly orthogonal (up, down, left, right) by one
     * step.
     * Outputs an error message if the move isn't orthogonal.
     * Returns true for an orthogonal move, false otherwise.
     */
    private boolean isMoveOrthogonal(int fromRow, int fromColumn, int toRow, int toColumn) {
        boolean orthogonal = Math.abs(toColumn - fromColumn) + Math.abs(toRow - fromRow) == 1;
        if (!orthogonal) {
            System.out.println("Error: You can only move 1 step to the left, right, up, or down(orthogonally).");
        }
        return orthogonal;
    }

    /**
     * Checks the entire board for any unordered piles of items.
     * Returns true if at least one pile is not in increasing order, indicating the
     * board is unordered.
     */
    private boolean isUnOrderedBoard() {
        boolean unorderedPile = false;
        for (int i = 0; i < rows && !unorderedPile; i++) {
            for (int j = 0; j < columns && !unorderedPile; j++) {
                if (!board[i][j].isInIncreasingOrder()) {
                    unorderedPile = true;
                }
            }
        }
        return unorderedPile;
    }

    @Override
    public boolean performAction(char c, String[] strings) {
        boolean isValid = true; // Flag to check the overall validity of the move.
        int fromRow = Integer.parseInt(strings[0]); // Extracted starting row from input.
        int fromColumn = Integer.parseInt(strings[1]); // Extracted starting column from input.
        int toRow = -1; // Initialize target row, will be set later.
        int toColumn = -1; // Initialize target column, will be set later.

        // Validate source cell's existence on the board.
        if (!isCellWithinBoard(fromRow, fromColumn)) {
            System.out.println("Error: Source cell not found on board. \n");
            isValid = false;
        }

        // Validate action type unless already invalidated.
        if (!(c == SPECIAL || c == MOVE || c == PEEK) && isValid) {
            System.out.println("Invalid action. Pick one of 'p', 'm' or 's'!");
            isValid = false;
        } else {
            // For actions requiring a destination, validate the input and destination cell.
            if (c != PEEK & isValid)
                if (strings.length > 3) {
                    toRow = Integer.parseInt(strings[2]);
                    toColumn = Integer.parseInt(strings[3]);
                    // Validate destination cell's existence on the board.
                    if (!isCellWithinBoard(toRow, toColumn)) {
                        System.out.println("Error: Destination cell not found on board. \n");
                        isValid = false;
                    } else {
                        // Check if the move is orthogonal and within one step.
                        isValid = isMoveOrthogonal(fromRow, fromColumn, toRow, toColumn);
                    }
                } else {
                    System.out.println("Error: Invalid desitnation move. \n");
                    isValid = false;
                }
        }

        // Process the valid move.
        if (isValid) {
            if (c == PEEK) {
                // Display the items at the source location for 'peek' action.
                System.out.println("Row " + fromRow + " Col " + fromColumn + " (bottom to top): "
                        + board[fromRow - 1][fromColumn - 1].toString());
            } else if (c == MOVE || c == SPECIAL) {
                // Fetch the item piles at source and destination.
                ItemPile from = board[fromRow - 1][fromColumn - 1];
                ItemPile to = board[toRow - 1][toColumn - 1];

                // Check if source pile is not empty.
                if (from.getSize() < 1) {
                    System.out.println("Error: You cant pick from an empty pile.");
                    isValid = false;
                } else {
                    // Process the item movement for 'move' or 'special' actions.
                    if (c == MOVE && (moves - playerMoves) > 0) {
                        int itemToMove = from.takeFromPile();

                        to.addToPile(itemToMove);
                        playerMoves++;
                    } else if (c == SPECIAL && (specialMoves - playerSMoves) > 0) {
                        int itemToMove = from.takeFromPile();

                        to.pushDown(itemToMove);
                        playerMoves++;
                        playerSMoves++;
                    } else {
                        System.out.println("Error: Out of Selected Move!!");
                    }
                }
                // Post-move check for game state.

                if (isValid) {
                    boolean unorderedPile = isUnOrderedBoard();
                    if ((moves - playerMoves) >= 0 && !unorderedPile) {
                        gameOver = true;
                        playerWon = true;
                    } else if ((moves - playerMoves) < 1 && unorderedPile) {
                        gameOver = true;
                        playerWon = false;
                    }
                }

            }

        }
        return isValid;
    }

    @Override
    public void printState() {
        for (ItemPile[] rows : board) {
            for (ItemPile cell : rows) {
                if (cell.seeTop() < 0) {
                    System.out.print("X  "); // Print 'X' for empty cells.
                } else if (!cell.isInIncreasingOrder()) {
                    System.out.print(cell.seeTop() + "* "); // Print top item with '*' for unordered piles.
                } else {
                    System.out.print(cell.seeTop() + "  "); // Print top item for ordered piles.
                }
            }
            System.out.println();
        }

        // Print the remaining moves and special moves available to the player.
        System.out.printf("You have %d move(s) remaining and %d special move(s).\n", moves - playerMoves,
                specialMoves - playerSMoves);
    }
}
