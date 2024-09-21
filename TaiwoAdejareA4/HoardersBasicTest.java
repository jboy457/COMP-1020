public class HoardersBasicTest extends TestLib {
    public static void main(String[] args) {
        runAllTests(null);
    }

    public static void runAllTests(TestCounter counter) {
        initializeTestSuite("basic Hoarders", counter);
        
        initializeTestCase("Construction");
        GameManagementInterface game = new Hoarders();
        assertNotNull(game);
    
        initializeTestCase("Bad Initialization");
        assertThrows(InitializationException.class, 
            () -> game.initializeGame(new String[] { "notaboard.txt" }));
        assertFalse(game.isInitialized());
            
        
        initializeTestCase("Play Simple Game");
        assertFalse(game.actionDescription().isEmpty());
        assertDoesNotThrow(
            () -> game.initializeGame(new String[] { "boards/testBoard1.txt" }));
        assertTrue(game.isInitialized());
        assertFalse(game.isGameOver());
        assertFalse(game.isGameWon());

        // NOTE: This type of output testing is generally bad practice. See comment in
        // TestLib if you're curious.
        assertOutputContains(() -> game.performAction('p', new String[] {"1", "1"}), 
            "1, 2");
        assertTrue(game.performAction('m', new String[] {"1", "1", "1", "2"}));
        assertTrue(game.isGameOver());
        assertTrue(game.isGameWon());

        initializeTestCase("No Moves Left");
        assertDoesNotThrow(
            () -> game.initializeGame(new String[] { "boards/testBoard2.txt" }));
        assertTrue(game.performAction('m', new String[] {"1", "1", "2", "1"}));
        assertTrue(game.performAction('m', new String[] {"1", "1", "2", "1"}));
        assertTrue(game.isGameOver());
        assertFalse(game.isGameWon());

        initializeTestCase("Super Move");
        game.resetGame();
        assertTrue(game.performAction('s', new String[] {"1", "1", "2", "1"}));
        assertTrue(game.isGameOver());
        assertTrue(game.isGameWon());

        initializeTestCase("Print State");
        game.resetGame();
        assertOutputContains(() -> game.printState(), "2*");
        game.performAction('s', new String[] {"1", "2", "2", "2"});
        String output = executeWithCapture(() -> game.printState());
        assertTrue(stringContains(output, "X"));
        assertFalse(stringContains(output, "4"));

        finalizeTestSuite();
    }
}

    