public class FairyDust {
    // FairyDust Attributes.
    private int sparkleLevel;
    private String color;
    static int totalDustCreated;

    /**
     * FairyDust Constructor
     * @paramters {sparkLevel, color}
     * @return FairyDust Object
     */
    public FairyDust(int sparkLevel, String color) {
        this.sparkleLevel = sparkLevel;
        this.color = color;
        totalDustCreated++;
    }

    public String toString() {
        return "FairyDust{sparkleLevel="+ this.sparkleLevel  +", color='" + this.color + "'}";
    }

    public int getSparkleLevel() {
        return this.sparkleLevel;
    }

    public String getColor() {
        return this.color;
    }

    static public int getTotalDustCreated(){
        return totalDustCreated;
    }

    public String useDust() {
        return "Using" + this.color + " fairy dust with sparkle level " + this.sparkleLevel;
    }

    public String describeDust() {
        return "This is " + this.color + " fairy dust with a sparkle level of " + this.sparkleLevel;
    }

    static public String describeTotalDust(){
        return "Total fairy dust created: " + totalDustCreated;
    }
}
