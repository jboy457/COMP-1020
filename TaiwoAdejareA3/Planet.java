public class Planet {
    private String name; // The name of the planet.

    /**
     * Constructs a Planet object with a specified name.
     * Input: Name of the planet.
     * Output: A new Planet object.
     * Parameters accepted: name (String) - The name of the planet.
     * Value returned: None.
     */
    public Planet (String name) {
        this.name = name;
    }

    /**
     * Returns the name of the planet.
     * Input: None.
     * Output: Name of the planet to the caller.
     * Parameters accepted: None.
     * Value returned: The name of the planet (String).
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the planet.
     * Input: New name for the planet.
     * Output: None (Updates the planet's name).
     * Parameters accepted: name (String) - The new name of the planet.
     * Value returned: None.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides a string representation of the Planet object.
     * Input: None.
     * Output: String representation of the Planet.
     * Parameters accepted: None.
     * Value returned: The string representation (String).
     */
    public String toString() {
        return "Planet{name='" + name + "'}";
    }

    /**
     * Checks if two Planet objects are equal based on their names.
     * Input: A Planet object to compare with.
     * Output: Boolean indicating if the two Planets have the same name.
     * Parameters accepted: planet (Planet) - The Planet object to compare.
     * Value returned: true if the names are the same, false otherwise (boolean).
     */
    public boolean equals(String planetName) {
        return this.name.equals(planetName);
    }
}