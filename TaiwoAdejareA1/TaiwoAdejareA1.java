/**
 * COMP 1020 SECTION [A03]
 * INSTRUCTOR: [Skyla Dudek]
 * NAME: [Adejare Taiwo]
 * ASSIGNMENT: [Assignment 1]
 * QUESTION: [question 1]
 *
 * PURPOSE: [You are implementing the data object classes for a magical Fairy
 * game.]
 */

public class TaiwoAdejareA1 {
    public static void main(String[] args) {
        // Fairy
        Fairy fairy = new Fairy("Serena", 20, 100);
        System.out.println(fairy.fly());
        System.out.println(fairy.castSpell());
        System.out.println(fairy);


        // Forest
        System.out.println();
        Forest forest = new Forest("Enchanted Woods");
        System.out.println(forest.addTree("Tree"));
        System.out.println(forest);

        // Fairy Dust
        System.out.println();
        FairyDust dust1 = new FairyDust(10, "Silver");
        System.out.println("Just Dust (toString), No Fairy");
        System.out.println(dust1);
        System.out.println("Use Dust");
        System.out.println(dust1.useDust());
        Fairy fairy1 = new Fairy("Tinkerbell", 100, 50);
        System.out.println("New Fairy String");
        System.out.println(fairy1);
        System.out.println("Describe Dust");
        System.out.println(fairy1.getDust().describeDust());
        System.out.println("Total Dust Created");
        System.out.println(FairyDust.describeTotalDust());


        // Enchantment
        System.out.println();
        Fairy fairy2 = new Fairy("Tinkerbell", 100, 75);
        EnchantedItem enchantedItem = new EnchantedItem("Magic Wand", 50, fairy2);
        System.out.println("Enchanted Item toString");
        System.out.println(enchantedItem);
        System.out.println("Enchanted Item in the fairy list");
        System.out.println(fairy2.isActiveItem(enchantedItem));       
        System.out.println("deactivateEnchantment");
        System.out.println(enchantedItem.deactivateEnchantment());
        System.out.println("trying to deactive again");
        System.out.println(enchantedItem.deactivateEnchantment());
        System.out.println("Enchanted Item NOT in the fairy list");
        System.out.println(fairy2.isActiveItem(enchantedItem));    
    }
}
