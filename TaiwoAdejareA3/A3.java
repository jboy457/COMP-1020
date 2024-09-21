import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class A3 {
    static Scanner scnr = new Scanner(System.in);
    final static String FINAL_REPORT = "finalReport.txt";

    public static void main(String[] args) {
        // Options
        final int HIRE_ALIEN = 1;
        final int DISCOVER_PLANET = 2;
        final int VISIT_ARTIFACT = 3;
        final int DEPOSIT_ARTIFACT = 4;
        final int QUIT = 5;

        // Option description
        final String[] OPTIONS = {
                "Hire a new Alien",
                "Discover a new Planet",
                "Visit an Artifact",
                "Deposit an Artifact",
                "Quit"
        };

        // Print welcome message
        System.out.println("\nWelcome to the Alien Artifact Vault Manager");
        System.out.println("===========================================");

        // Initalize VM (Start vm) and get inputed options.
        VaultManager vm = new VaultManager();
        int selectedOption = getValidAction(OPTIONS);

        // Keep showing options until QUIT is selected.
        while (selectedOption != QUIT) {
            switch (selectedOption) {
                case HIRE_ALIEN:
                    System.out.println(hireNewAlien(vm));
                    break;
                case DISCOVER_PLANET:
                    System.out.println(discoverPlanet(vm));
                    break;
                case VISIT_ARTIFACT:
                    System.out.println(visitArtifact(vm));
                    break;
                case DEPOSIT_ARTIFACT:
                    System.out.println(depositArtifact(vm));
                    break;
                default:
                    break;
            }
            // Get selected option after completing any of above actions.
            selectedOption = getValidAction(OPTIONS);
        }

        // Generate final vm report and terminate program.
        finalReport(vm);
        System.out.println("Thanks for Contributing to the Expedition!");

        scnr.close();
    }

    /**
     * Validates user's action selection against available options.
     * Input: String[] actions - possible actions.
     * Output: Informs user of valid input range on invalid selection.
     * Returns: int - index of valid selected action.
     * Loops until valid input is received, then returns its integer index.
     */
    public static int getValidAction(String[] actions) {
        String selectedAction = getActionInput(actions);

        // Keep asking until valid input is entered.
        while (!optionInputValid(actions.length, selectedAction)) {
            System.out.printf("Input must be between 1 and %d\n\n", actions.length);
            selectedAction = getActionInput(actions);
        }

        // return option number in integer.
        return Integer.parseInt(selectedAction);
    }

    /**
     * Checks if a given option is a valid selection based on menu length.
     * Input: menuLength (int) - number of options, option (String) - user's
     * selection.
     * Output: None directly, but evaluates the validity of the input option.
     * Returns: boolean - true if option is a valid selection; false otherwise.
     * Validates option against menu length and numeric criteria, ensuring it's
     * within range.
     */
    public static boolean optionInputValid(int menuLength, String option) {
        boolean isValid = false;
        isValid = isStrANumber(option);

        if (isValid) {
            int intOption = Integer.parseInt(option);

            // Check if number inputed is less than menu length.
            if (intOption > menuLength || intOption < 1) {
                isValid = false;
            }

        }

        return isValid;
    }

    /**
     * Determines if a string represents a numeric value.
     * Input: str (String) - the string to evaluate.
     * Output: None directly, but assesses the string's numeric validity.
     * Returns: boolean - true if the string is numeric; false otherwise.
     * Iterates through string characters to check for numeric composition, stopping
     * at first non-numeric character.
     */
    public static boolean isStrANumber(String str) {
        boolean isNumber = false;
        boolean errFound = false;
        // Check if input is valid digit
        for (int i = 0; i < str.length() && !errFound; i++) {
            char tempChar = str.charAt(i);
            if (Character.isDigit(tempChar)) {
                isNumber = true;
            } else {
                isNumber = false;
                errFound = true;
            }
        }

        return isNumber;
    }

    /**
     * Retrieves user's action input and displays menu options.
     * Input: menuStrings (String[]) - array of menu option descriptions.
     * Output: Displays menu options to the user.
     * Returns: String - the user's selected action input.
     * Prompts user for action selection, presenting each menu option for clarity.
     */
    public static String getActionInput(String[] menuStrings) {
        System.out.println();
        System.out.println("Please select one of the following options:");
        for (int i = 1; i <= menuStrings.length; i++) {
            System.out.println(i + " - " + menuStrings[i - 1]);
        }
        String selected = promptInput();
        return selected;
    }

    /**
     * Prompts user for input and returns the entered string.
     * Input: None.
     * Output: User's input from the console.
     * Parameters accepted: None.
     * Value returned: Entered string (String).
     */
    public static String promptInput() {
        System.out.print(">>>  ");
        String input = scnr.nextLine();
        return input;
    }

    /**
     * Hires a new alien after collecting its first name, last name, and access
     * level.
     * Input: None.
     * Output: The result of hiring the alien, typically a confirmation message.
     * Parameters accepted: VaultManager vm (to manage hiring process).
     * Value returned: Result of the hiring process (String).
     */
    public static String hireNewAlien(VaultManager vm) {
        // Get firstname
        System.out.println("Enter the Alien's first name:");
        String firstName = promptInput();

        // Get lastname
        System.out.println("Enter the Alien's last name:");
        String lastName = promptInput();

        // Get access level and convert it to an integer
        System.out.println("Enter the Alien's access level:");
        String accessLevelStr = promptInput();
        int accessLevel = 0;
        // Always throw until valid number is entered.
        while (!isStrANumber(accessLevelStr)) {
            System.out.println("Access level must be a number!");
            accessLevelStr = promptInput();
        }

        // Return hired alien.
        return vm.hireAlien(new Alien(firstName, lastName, accessLevel));
    }

    /**
     * Initiates the discovery of a new planet by name.
     * Input: None.
     * Output: The result of discovering the planet, typically a status message.
     * Parameters accepted: VaultManager vm (to manage the discovery process).
     * Value returned: Result of the planet discovery process (String).
     */
    public static String discoverPlanet(VaultManager vm) {
        // Get planet name
        System.out.println("Enter the name of the new Planet: ");
        String planetName = promptInput();

        // return discover planet response.
        return vm.discoverPlanet(new Planet(planetName));
    }

    /**
     * Deposits an artifact by name into the vault.
     * Input: None.
     * Output: The result of the artifact deposit, typically a confirmation message.
     * Parameters accepted: VaultManager vm (to manage the deposit process).
     * Value returned: Result of the artifact deposit process (String).
     */
    public static String depositArtifact(VaultManager vm) {
        // Get artifacts name
        System.out.println("Enter the Artifact's name: ");
        String artifactName = promptInput();

        // return deposited artifact
        return vm.depositArtifact(artifactName);
    }

    /**
     * Initiates a visit to an artifact by an alien.
     * Input: None.
     * Output: The result of the artifact visit, typically an access status message.
     * Parameters accepted: VaultManager vm (to facilitate the selection and visit
     * process).
     * Value returned: Result of the artifact visit process (String).
     */
    public static String visitArtifact(VaultManager vm) {
        // Get alien name after printing out aliens
        System.out.println("Please select which Alien will be visiting the Artifact:");
        System.out.println(vm.stringifyAliens());
        String selectedAlien = promptInput();

        // Get artifacts name after printing out artifacts
        System.out.println("Please select which Artifact will be visited: ");
        System.out.println(vm.stringifyArtifacts());
        String selectedArtifact = promptInput();

        // Return resposne based on selections
        return vm.visitArtifact(selectedArtifact, selectedAlien);
    }

    /**
     * Generates and saves a final report detailing aliens, planets, and artifacts.
     * Input: None.
     * Output: Saves the report to a file, with error messages printed on failure.
     * Parameters accepted: VaultManager vm (source of data for the report).
     * Value returned: None.
     */
    public static void finalReport(VaultManager vm) {
        try {
            // format and combine details to be printed in report
            String finalReport = vm.stringifyAliens() + "\n\n" + vm.stringifyPlanets() + "\n\n"
                    + vm.stringifyArtifacts();

            // Initalize buffer reader and file wirter
            FileWriter fileWriter = new FileWriter(FINAL_REPORT);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // write report into file
            bufferedWriter.write(finalReport);
            bufferedWriter.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

    }
}
