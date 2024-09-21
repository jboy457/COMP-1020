import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VaultManager {
    private Artifact[] artifacts;
    private ArrayList<Alien> aliens;
    private Planet[] planets;
    public static int planetPointer = 0;
    public static int artifactPointer = 0;
    private final int SIZE = 10;

    // Vault File
    private final String VAULT_FILENAME = "galactic_vault.txt";
    private final String ALIEN_FILENAME = "aliens.txt";

    public VaultManager() {
        // Initialze Planet, Aritfacts and aliens.
        planets = new Planet[SIZE];
        artifacts = new Artifact[SIZE];
        aliens = new ArrayList<Alien>();

        // Load vault and aliens into vault manager.
        loadVault(VAULT_FILENAME);
        loadAliens(ALIEN_FILENAME);
    }

    /**
     * Compiles names and access levels of all aliens into a string.
     * Input: None.
     * Output: String listing aliens with names and access levels.
     * Parameters accepted: None.
     * Value returned: Aliens' names and access levels formatted as a string
     * (String).
     */
    public String stringifyAliens() {
        String alienStr = "[";
        for (int i = 0; i < aliens.size(); i++) {
            Alien alien = aliens.get(i);
            alienStr += "Name: " + alien.getName() + ", " + "Access Level: "
                    + alien.getAccessLevel();
            if (i < aliens.size() - 1)
                alienStr += "\n";
        }
        alienStr += "]";

        return alienStr;
    }

    /**
     * Generates a detailed string representation of all artifacts.
     * Input: None.
     * Output: Detailed information of artifacts, formatted as a string.
     * Parameters accepted: None.
     * Value returned: String with artifacts' details (String).
     */
    public String stringifyArtifacts() {
        String artifactStr = "[";
        for (int i = 0; i < artifactPointer; i++) {
            artifactStr += "Artifact: " + artifacts[i].getName() + " was found on planet "
                    + artifacts[i].getPlanet().getName() + " during " + artifacts[i].getDiscoveryDate() + ".\n";
            artifactStr += "Currently stored in " + artifacts[i].getStorageLocation()
                    + " requiring security clearance level " + artifacts[i].getSecurityLevel() + ".\n";
            artifactStr += "Description: " + artifacts[i].getDescription();
            if (i < artifactPointer - 1)
                artifactStr += "\n\n";
        }
        artifactStr += "]";
        return artifactStr;
    }

    /**
     * Concatenates names of planets in the array into a single string.
     * Input: None.
     * Output: String of planet names enclosed in brackets and separated by commas.
     * Parameters accepted: None.
     * Value returned: A string representation of planet names (String).
     */
    public String stringifyPlanets() {
        String planetStr = "[";
        for (int i = 0; i < planetPointer; i++) {
            planetStr += planets[i].getName();
            if (i < planetPointer - 1)
                planetStr += ", ";
        }
        planetStr += "]";

        return planetStr;
    }

    /**
     * Decodes a string encoded with letters and layers separated by "|" and spaces.
     * Input: Encoded string with custom encoding.
     * Output: Decoded string.
     * Parameters accepted: encodedStr (String) - The encoded string to decode.
     * Value returned: The decoded string (String).
     */
    public String decodeArtifactName(String encodedStr) {
        // Split the encoded string to words
        String[] encodedWords = encodedStr.split(" ");
        String decodedWord = "";

        // Iterate over each encoded word
        for (int i = 0; i < encodedWords.length; i++) {
            // Split each word into encoded characters.
            String[] encodedLetters = encodedWords[i].split("\\|");
            // Decoded each letter and add it to the result
            for (String encodedLetter : encodedLetters) {
                char letter = Character.toUpperCase(encodedLetter.charAt(0));
                int layer = Integer.parseInt(encodedLetter.substring(1));
                decodedWord += decodedLetter(letter, layer);
            }
            // Add space between each words.
            if (i < encodedWords.length - 1)
                decodedWord += " ";
        }

        return decodedWord;

    }

    /**
     * Decodes a letter based on a specific encoding layer.
     * Input: The letter to decode and its encoding layer.
     * Output: The decoded letter.
     * Parameters accepted:
     * letter (char) - The letter to decode.
     * layer (int) - The encoding layer number.
     * Value returned: The decoded letter (char).
     */
    private char decodedLetter(char letter, int layer) {
        // key to decrypt each letter.
        final char[] KEY = { 'H', 'Z', 'A', 'U', 'Y', 'E', 'K', 'G', 'O', 'T', 'I', 'R', 'J', 'V', 'W', 'N', 'M', 'F',
                'Q',
                'S', 'D', 'B', 'X', 'L', 'C', 'P' };

        // Base case: return the mirror of the of the last layer
        if (layer == 1) {
            int number = (int) 'Z' - letter;
            return (char) (number + 'A');
        }
        // Recursively decode each letter.
        return decodedLetter(KEY[letter - 'A'], layer - 1);
    }

    /**
     * Decodes aliens from a file and adds them to the aliens array.
     * Input: File name containing aliens.
     * Output: None.
     * Parameters accepted: fileName (String) - The file name with
     * aliens data.
     * Value returned: None (void).
     */
    public void loadAliens(String fileName) {
        try {
            // Initalize buffer reader and file reader to read file connect.
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read line by line and add new aliens.
            String bufferLine = bufferedReader.readLine();
            while (bufferLine != null) {
                // Split the aliens string into details.
                String[] aliensDetail = bufferLine.split(",");
                String[] names = aliensDetail[0].split(" ");
                Alien newAlien = new Alien(names[0], names[1], Integer.parseInt(aliensDetail[1].trim()));
                Alien alienExist = findAlien(newAlien.getName());
                if (alienExist == null) {
                    aliens.add(newAlien);
                }
                bufferLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException err) {
            System.out.println("Error: " + err.getMessage());
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }

    /**
     * Decodes artifacts from a file and adds them to the artifacts array.
     * Input: File name containing encoded artifacts.
     * Output: None.
     * Parameters accepted: vaultFileName (String) - The file name with encoded
     * artifact data.
     * Value returned: None (void).
     */
    public void loadVault(String valutFileName) {
        try {
            // Initalize buffer reader and file reader to read file connect.
            FileReader fileReader = new FileReader(valutFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read line by line and create new artifact.
            String bufferLine = bufferedReader.readLine();
            while (bufferLine != null) {

                // Split the encodedArtifact string into details.
                String[] artifactDetail = bufferLine.split(",");
                Artifact newArtifact = createArtifact(artifactDetail);
                // Add artifact to artifacts list.
                artifacts[artifactPointer] = newArtifact;
                artifactPointer++;
                bufferLine = bufferedReader.readLine();
            }
            // Sort the artifacts after reading the vault file.
            sortVault();
            bufferedReader.close();
        } catch (FileNotFoundException err) {
            System.out.println("Error: " + err.getMessage());
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }

    private Planet createPlanet(String plantName) {
        Planet planet = new Planet(plantName);

        // Add planet to planets list.
        planets[planetPointer] = planet;
        planetPointer++;
        return planet;
    }

    /**
     * Adds a new Artifact from encoded data to artifacts array.
     * Input: Comma-separated artifact details.
     * Output: None.
     * Parameters accepted: encodedArtifact (String) - Encoded artifact data.
     * Value returned: None (void).
     */
    private Artifact createArtifact(String[] artifactDetail) {
        // Create planet - Ensure planet exist before createing artifact.
        Planet artifactPlanet = findPlanet(artifactDetail[1]);
        if (artifactPlanet == null)
            artifactPlanet = createPlanet(artifactDetail[1]);

        // Create a new artifact object with the decoded name.
        Artifact artifact = new Artifact(
                decodeArtifactName(artifactDetail[0]),
                artifactPlanet,
                artifactDetail[2].trim(), // artifact discovery date
                Integer.parseInt(artifactDetail[3]), // artifact security level
                artifactDetail[5].trim(), // artifact description
                artifactDetail[4].trim() // artifact storageLocation
        );

        return artifact;
    }

    /**
     * Searches for a Planet by its name.
     * Input: Name of the Planet to find.
     * Output: The Planet object with the matching name, or null if not found.
     * Parameters accepted: planetName (String) - The name of the Planet to search
     * for.
     * Value returned: Planet object if found, null otherwise (Planet).
     */
    private Planet findPlanet(String planetName) {
        boolean isFound = false;
        Planet planet = null;
        // Iterate over the planets up to planet Pointer or planet is found.
        for (int i = 0; i < planetPointer && !isFound; i++) {

            // Assign planet if the name matches.
            if (planets[i].equals(planetName)) {
                planet = planets[i];
                isFound = true;
            }
        }
        return planet;
    }

    /**
     * Searches for a Aritfact by its name.
     * Input: Name of the Artifact to find.
     * Output: The artifact object with the matching name, or null if not found.
     * Parameters accepted: artifactName (String) - The name of the artifact to
     * search
     * for.
     * Value returned: artifact object if found, null otherwise (artifact).
     */
    private Artifact findArtifact(String artifactName) {
        int first = 0, last = artifactPointer - 1;
        boolean isFound = false;
        Artifact artifact = null;
        while (first <= last && !isFound) {
            int mid = first + (last - first) / 2;

            // Check if its mid artifact
            if (artifacts[mid].getName().equals(artifactName)) {
                artifact = artifacts[mid];
                isFound = true;
            }

            // if artifact first letter is greater than mid artifact ignore first half
            if (artifacts[mid].getName().charAt(0) < artifactName.charAt(0) && !isFound)
                first = mid + 1;

            // if artifact first letter is less than mid artifact ignore last half
            if (artifacts[mid].getName().charAt(0) > artifactName.charAt(0) && !isFound)
                last = mid - 1;
        }

        return artifact;
    }

    /**
     * Sort arfact array list in ascending order using insertion sort.
     * Input: None.
     * Output: None.
     * Value returned: None (void)
     * 
     */
    public void sortVault() {
        for (int i = 1; i < artifactPointer; i++) {
            orderedInsert(artifactPointer - 1, artifacts[i]);
        }
    }

    /**
     * Inserts an artifact into the array in order based on the first character of
     * the artifact's name.
     * Input: artifactPointer (index for insertion start), newArtifact (artifact to
     * insert).
     * Output: None.
     * Parameters accepted: int artifactPointer, Artifact newArtifact.
     * Value returned: None.
     */
    public void orderedInsert(int artifactPointer, Artifact newArtifact) {
        int j = artifactPointer - 1;
        // Move artifact greater than newArtifact
        while (j >= 0 && artifacts[j].getName().charAt(0) > newArtifact.getName().charAt(0)) {
            artifacts[j + 1] = artifacts[j];
            j -= 1;
        }

        artifacts[j + 1] = newArtifact;
    }

    public String hireAlien(Alien newAlien) {
        String response;
        Alien alienExist = findAlien(newAlien.getName());

        if (alienExist != null) {
            response = "Alien " + newAlien.getName() + " already works here.";
        } else {
            aliens.add(newAlien);
            response = "Alien " + newAlien.getName() + " has been hired.";
        }

        return response;
    }

    /*
     * Deposit an artifact read from file and orderly inserted into the list of
     * artifacts.
     * Input: String artifactName - the name of the artifact file (excluding file
     * extension).
     * Output: String - A response message indicating the outcome of the operation.
     */
    public String depositArtifact(String artifactName) {
        String response = null;
        try {
            // Initalize buffer reader and file reader to read file connect.
            FileReader fileReader = new FileReader(artifactName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read artifact line and create new artifact.
            String[] newArtifact = bufferedReader.readLine().split(",");

            // Decoded artifact name and check if its already exist
            String newArtifactName = decodeArtifactName(newArtifact[0]);
            Artifact artifactExist = findArtifact(newArtifactName);

            // Create and insert if not and return appropriate response.
            if (artifactExist == null) {
                Artifact artifact = createArtifact(newArtifact);
                // Orderly add artifact to artifacts list.
                orderedInsert(artifactPointer, artifact);
                artifactPointer++;
                response = "Artifact " + artifact.getName() + " has been deposited.";
            } else {
                response = "Artifact " + artifactExist.getName() + " is already stored.";
            }
            bufferedReader.close();
        } catch (FileNotFoundException err) {
            response = "Artifact file for " + artifactName + " does not exist.";
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
        return response;
    }

    /**
     * Discovers a new planet or acknowledges an already known one.
     * Input: planet (planet to discover).
     * Output: A message indicating whether the planet was discovered or is already
     * known.
     * Parameters accepted: Planet planet.
     * Value returned: A response message (String).
     */
    public String discoverPlanet(Planet planet) {
        String response = null;
        // Finds artifact planet
        Planet artifactPlanet = findPlanet(planet.getName());

        if (artifactPlanet == null) {
            // add planet to list of planet its doesnt exist
            planets[planetPointer] = planet;
            planetPointer++;
            response = "Planet " + planet.getName() + " has been discovered.";
        } else {
            response = "Planet " + planet.getName() + " is already known.";
        }
        return response;
    }

    /**
     * Evaluates an alien's access to a specified artifact.
     * Input: artifactName (name of artifact), alienName (name of alien).
     * Output: A message indicating access status or existence of alien/artifact.
     * Parameters accepted: String artifactName, String alienName.
     * Value returned: Access status message (String).
     */
    public String visitArtifact(String artifactName, String alienName) {
        String response = null;
        // Get alien and artifact to visit
        Alien alienExist = findAlien(alienName);
        Artifact artifactExist = findArtifact(artifactName.toUpperCase());

        // return response based on findings.
        if (alienExist == null) {
            response = "Alien " + alienName + " does not exist.";
        } else if (artifactExist == null) {
            response = "Artifact " + artifactName + " doesnt not exist";
        } else if (alienExist.getAccessLevel() < artifactExist.getSecurityLevel()) {
            response = "Access Denied";
        } else {
            response = "Access Granted";
        }
        return response;

    }

    /**
     * Searches for an alien by name.
     * Input: alienName (name of the alien to find).
     * Output: Found alien or null if not found.
     * Parameters accepted: String alienName.
     * Value returned: Alien object or null (Alien).
     */
    private Alien findAlien(String alienName) {
        Alien alien = null;
        boolean foundAlien = false;

        // Linearly search for the alien
        for (int i = 0; i < aliens.size() && !foundAlien; i++) {
            if (aliens.get(i).equals(alienName)) {
                alien = aliens.get(i);
                foundAlien = true;
            }
        }
        return alien;
    }

}
