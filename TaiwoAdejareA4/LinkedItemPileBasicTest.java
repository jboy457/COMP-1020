public class LinkedItemPileBasicTest extends TestLib {
    public static void main(String[] args) {
        runAllTests(null);
    }

    public static void runAllTests(TestCounter globalCounter) {
        initializeTestSuite("basic LinkedItemPile", globalCounter);
        
        initializeTestCase("Construction");
        ItemPile pile = new LinkedItemPile();
        assertNotNull(pile);
    
        initializeTestCase("Empty");
        assertTrue(pile.isEmpty());
        assertEqual(pile.getSize(), 0);
        assertEqual(pile.seeTop(), -1);
        assertEqual(pile.takeFromPile(), -1);
    
        initializeTestCase("Insertion");
        assertEqual(pile.addToPile(-2), -1);
        assertTrue(pile.isEmpty());
        assertEqual(pile.addToPile(1), 1);
        assertFalse(pile.isEmpty());
        assertEqual(pile.getSize(), 1);
        assertEqual(pile.seeTop(), 1);

        initializeTestCase("Push Down");
        assertEqual(pile.pushDown(2), 2);
        assertEqual(pile.getSize(), 2);
        assertEqual(pile.seeTop(), 1);
        assertTrue(pile.isInIncreasingOrder());

        initializeTestCase("Removal");
        assertEqual(pile.takeFromPile(), 1);
        assertEqual(pile.getSize(), 1);
        assertEqual(pile.takeFromPile(), 2);
        assertTrue(pile.isEmpty());

        finalizeTestSuite();
    }
}
