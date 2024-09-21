public class Fairy {
    final private int ITEM_MAX = 100;

    // Fiary Attributes.
    private String name;
    private int age;
    private int magicLevel;
    private FairyDust dust;
    private EnchantedItem[] items;
    private int itemCount;

    /**
     * Fiary Constructor
     * @paramters {name, age, magicLevel}
     * @return Fairy Object
     */
    public Fairy(String name, int age, int magicLevel) {
        this.name = name;
        this.age = age;
        this.magicLevel = magicLevel;
        this.dust = setDust();
        this.items = new EnchantedItem[ITEM_MAX];
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getMagicLevel() {
        return this.magicLevel;
    }

    public FairyDust getDust() {
        return this.dust;
    }

    public EnchantedItem[] getItems() {
        return this.items;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    /**
     * toString - Fiary Object parsed to string
     * @parameter - No paramter.
     * @return String Fairy
     */
    public String toString() {
        return "Fairy{name='" + this.name + "', age=" + this.age + ", magicLevel=" + this.magicLevel + ", dust="
                + this.dust.toString() + "}";
    }

    /**
     * setDust - set fairy dust based on magic level.
     * @parameter - No paramter.
     * @return FairyDust dustRes
     */
    private FairyDust setDust() {
        FairyDust dustRes;
        final int BASIC_MAX = 40; 

        // Basic dust fairies details
        final String BDUST_COLOR = "Gold";
        final int BLEVEL_MIN = 1;
        final int BLEVEL_MAX = 20;
        final int BLEVEL_RANGE = BLEVEL_MAX - BLEVEL_MIN + 1;

        int bSparkleLevel = (int) (Math.random() * BLEVEL_RANGE) + BLEVEL_MIN;

        // Super dust fairies details
        final String SDUST_COLOR = "Gold";
        final int SLEVEL_MIN = 21;
        final int SLEVEL_MAX = 50;
        final int SLEVEL_RANGE = SLEVEL_MAX - SLEVEL_MIN + 1;

        int sSparkleLevel = (int) (Math.random() * SLEVEL_RANGE) + SLEVEL_MIN;

        // Assign dust response based on magic level.
        if (this.magicLevel < BASIC_MAX) {
            dustRes = new FairyDust(bSparkleLevel, BDUST_COLOR);
        } else {
            dustRes = new FairyDust(sSparkleLevel, SDUST_COLOR);
        }

        return dustRes;
    }

    /**
     * castSpell - determines the type of magical spell based on magic level.
     * @parameter - No paramter.
     * @return String message.
     */
    public String castSpell() {
        final int SPELL_MAX = 40;
        String message;

        if (magicLevel > SPELL_MAX) {
            message = this.name + " casts a SUPER magical spell!";
        } else {
            message = this.name + " casts a magical spell!";
        }

        return message;
    }

    /**
     * fly - method to fly fairy.
     * @parameter - No paramter.
     * @return String response.
     */
    public String fly() {
        return this.name + " is flying!";
    }

    /**
     * addItem - this method adds enchanted item to fairy.
     * @parameter - { EnchantedItem item }
     * @return void.
     */
    public void addItem(EnchantedItem item) {
        // Add item if not greater than maximum item (100).
        if (this.itemCount < this.ITEM_MAX) {
            this.items[this.itemCount] = item;
            itemCount++;
        }
    }

    /**
     * removeItem - this method removes enchanted item from fairy.
     * @parameter - { String enchantName }
     * @return void.
     */
    public void removeItem(String enchantName) {
        EnchantedItem[] newItems = new EnchantedItem[ITEM_MAX];
        int newItemIndex = 0;

        // skip item from and put into a new array
        for (int i = 0; i < this.itemCount; i++) {
            if (!this.items[i].getItemName().equals(enchantName)) {
                newItems[newItemIndex++] = this.items[i];
            }
        }

        // copy item back into old array
        for (int i = 0; i < this.itemCount; i++) {
            this.items[i] = newItems[i];
        }

        itemCount--;
    }

    /**
     * isActiveItem - this check if enchanted item still exist in fairy
     * @parameter - { EnchantedItem enchantItem }
     * @return boolean itemExist.
     */
    public boolean isActiveItem(EnchantedItem enchantItem) {
        boolean itemExist = false;
        int tempCount = 0;
        while (!itemExist && tempCount < this.itemCount) {
            if (this.items[tempCount].getItemName().equals(enchantItem.getItemName())) {
                itemExist = true;
            }
            tempCount++;
        }

        return itemExist;
    }
}
