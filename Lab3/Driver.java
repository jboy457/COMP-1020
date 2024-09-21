public class Driver {
    private String name;
    private String dob;
    private int exp;
    private boolean glasses;

    public Driver(String name, String dob, int exp, boolean glasses) {
        this.name = name;
        this.dob = dob;
        this.exp = exp;
        this.glasses = glasses;
    }

    public void updateExpiry(int year) {
        if(year <= exp) {
            System.out.println("License exp yar canonly be increased");
        } else {
            exp = year;
        }
    }

    public String toString() {
        return "Driver: " + name + " dob: " + dob + " expiry " + exp + " glasses: " + glasses;
    }
}
