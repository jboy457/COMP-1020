public class Alien {
    private String firstName;
    private String lastName;
    private String fullName;
    private int accessLevel;

    /**
     * Constructor for Alien object.
     * Input: First name, last name, and access level of the alien.
     * Output: A new Alien object.
     * Parameters accepted:
     *  - firstName (String): The first name of the alien.
     *  - lastName (String): The last name of the alien.
     *  - accessLevel (int): The access level of the alien.
     * Value returned: None.
     */
    public Alien (String firstName, String lastName, int accessLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.accessLevel = accessLevel;
    }

    /**
     * Gets the first name of the alien.
     * Input: None.
     * Output: First name of the alien.
     * Parameters accepted: None.
     * Value returned: First name (String).
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the alien.
     * Input: None.
     * Output: Last name of the alien.
     * Parameters accepted: None.
     * Value returned: Last name (String).
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the access level of the alien.
     * Input: None.
     * Output: Access level of the alien.
     * Parameters accepted: None.
     * Value returned: Access level (int).
     */
    public int getAccessLevel() {
        return accessLevel;
    }

    public String getName() {
        return  firstName + " " + lastName;
    }

    /**
     * Checks if two Alien objects are equal based on their first name.
     * Input: An Alien object to compare with.
     * Output: Boolean indicating if the two Aliens have the same first name.
     * Parameters accepted:
     *  - alien (Alien): The Alien object to compare.
     * Value returned: true if the first names match, false otherwise (boolean).
     */
    public boolean equals(String alienName) {
        return this.fullName.toLowerCase().equals(alienName.toLowerCase());
    } 
}