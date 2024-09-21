public class EnchantedItem {
    // EnchantedItem Attributes.
    private String itemName;
    private int enchantmentPower;
    private Fairy enchantedBy;
    private boolean isActivated = true;

     /**
     * EnchantedItem Constructor
     * @paramters {itemName, enchantmentPower, enchantedBy}
     * @return EnchantedItem Object
     */
    public EnchantedItem(String itemName, int enchantmentPower, Fairy enchantedBy) {
        this.itemName = itemName;
        this.enchantmentPower = enchantmentPower;
        this.enchantedBy = enchantedBy;

        // Add Enchanted object to fairy
        this.enchantedBy.addItem(this);
    }

    public String toString() {
        return "EnchantedItem{itemName='" + this.itemName + "', enchantmentPower=" + this.enchantmentPower + ", enchantedBy=" + this.enchantedBy.getName() +"}";
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getEnchantmentPower() {
        return this.enchantmentPower;
    }

    public Fairy getEnchantedBy() {
        return this.enchantedBy;
    }

    public boolean getIsActivated() {
        return this.isActivated;
    }

    /**
     * isEnchantedBy - return the fairy has this enchantment item 
     * @paramters {Fairy fairy}
     * @return boolean
     */
    public boolean isEnchantedBy(Fairy fairy) {
        return fairy.isActiveItem(this);
    }

    /**
     * deactivateEnchantment - remove enchament from fairy
     * @paramters - No paramter!
     * @return String responseMsg.
     */
    public String deactivateEnchantment() {
        String responseMsg;
        // Check if item exist before removing item from fairy.
        if(this.enchantedBy.isActiveItem(this)) {
            this.enchantedBy.removeItem(this.itemName);
            responseMsg = "Deactivating the enchantment of " + this.itemName;
        } else {
            responseMsg = this.itemName + " is already deactivated";
        }
      
        return responseMsg;
    }
}
