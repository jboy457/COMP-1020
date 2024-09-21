public class Artifact {
    private String name;
    private Planet planet;
    private String discoveryDate;
    private int securityLevel;
    private String description;
    private String storageLocation;

    /**
     * Constructs an Artifact object with specified details.
     * Input: Details of the artifact.
     * Output: A new Artifact object.
     * Value returned: None.
     */
    public Artifact (String name, Planet planet, String discoveryDate, int securityLevel, String description, String storageLocation) {
        this.name = name;
        this.planet = planet;
        this.discoveryDate = discoveryDate;
        this.securityLevel = securityLevel; 
        this.description = description;
        this.storageLocation = storageLocation;
    }

    /**
     * Returns the name of the artifact.
     * Input: None.
     * Output: Name of the artifact to the caller.
     * Parameters accepted: None.
     * Value returned: The name of the artifact (String).
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the planet associated with the artifact.
     * Input: None.
     * Output: Planet associated with the artifact.
     * Parameters accepted: None.
     * Value returned: The planet object (Planet).
     */
    public Planet getPlanet() {
        return planet;
    }

    /**
     * Returns the description of the artifact.
     * Input: None.
     * Output: Description of the artifact to the caller.
     * Parameters accepted: None.
     * Value returned: The description of the artifact (String).
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the discovery date of the artifact.
     * Input: None.
     * Output: Discovery date of the artifact.
     * Parameters accepted: None.
     * Value returned: The discovery date (String).
     */
    public String getDiscoveryDate() {
        return discoveryDate;
    }

    /**
     * Returns the security level of the artifact.
     * Input: None.
     * Output: Security level of the artifact.
     * Parameters accepted: None.
     * Value returned: The security level (int).
     */
    public int getSecurityLevel() {
        return securityLevel;
    }

    /**
     * Returns the storage location of the artifact.
     * Input: None.
     * Output: Storage location of the artifact.
     * Parameters accepted: None.
     * Value returned: The storage location (String).
     */
    public String getStorageLocation() {
        return storageLocation;
    }

    /**
     * Checks if two Artifact objects are equal based on their name and storage location.
     * Input: An Artifact object to compare with.
     * Output: Boolean indicating if the two Artifacts are considered equal.
     * Parameters accepted: 
     *  - artifact (Artifact): The Artifact object to compare.
     * Value returned: true if the names and storage locations match, false otherwise (boolean).
     */
    public boolean equals(String artifactName, String storageLoaction) {
        return this.name.equals(artifactName) && this.storageLocation.equals(storageLoaction);
    }
}