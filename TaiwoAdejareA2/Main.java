
/**
 * COMP 1020 SECTION [A03]
 * INSTRUCTOR: [Skyla Dudek]
 * NAME: [Adejare Taiwo]
 * ASSIGNMENT: [Assignment 2]
 * QUESTION: [question 1]
 *
 * PURPOSE: [Make a prototype of a virtual journal that is a collection of text files, each representing a different Pokémon as well as a storage system for trainers to use for their Pokémon]
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scnr = new Scanner(System.in);
    private static final String POKEMON_DB = "pokemon.txt";

    // List of pokemons in PC and journals.
    private static ArrayList<String> journals = new ArrayList<String>();
    private static ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

    public static void main(String[] args) {
        // All menu items
        final int CATCH = 1;
        final int PRINT_PC = 2;
        final int DELETE = 3;
        final int PRINT_JOURNAL = 4;
        final int EXIT = 5;

        // Action description.
        String[] ACTIONS = {
                "Catch a new Pokemon",
                "Print all Pokemon in PC",
                "Delete a Pokemon from PC",
                "Print Pokemon Info from Journal",
                "Quit"
        };

        // Get pokemon count and check if sheet is empty
        int pokemonLineCount = getPokemonLineCount();
        if (pokemonLineCount > 0) {
            int selectedOption = getValidAction(ACTIONS);

            // Always show menu options until quit is selected.
            while (selectedOption != EXIT) {
                switch (selectedOption) {
                    case CATCH:
                        catchNewPokemon(pokemonLineCount);
                        break;
                    case PRINT_PC:
                        printPCPokemon();
                        break;
                    case DELETE:
                        deletePokemon();
                        break;
                    case PRINT_JOURNAL:
                        printJournalPokemon();
                        break;
                    default:
                        break;
                }
                selectedOption = getValidAction(ACTIONS);
            }
        }
        System.out.println("Program terminated successfully!!!!");
    }

    /**
     * Deletes a specified Pokemon from the list based on user input for the
     * Pokemon's name and, if necessary, its ID.
     * Input: Name of the Pokemon to delete, and the ID if multiple Pokemon with the
     * same name exist.
     * Output: Removes the specified Pokemon from the list and prints a confirmation
     * message.
     * Value returned: None (void method),
     *
     */
    public static void deletePokemon() {
        try {
            // Prompt user to input the name of the pokemon to be deleted
            System.out.print("What Pokemon do you want to delete?: ");
            String name = scnr.nextLine();
            name = name.toLowerCase();

            ArrayList<Pokemon> foundPokemons = new ArrayList<Pokemon>();
            Pokemon pokemonToDelete = null;

            // Iterate over the list of pokemons to find the name that matches.
            for (Pokemon pokemon : pokemons) {
                if (pokemon.getName().toLowerCase().equals(name)) {
                    foundPokemons.add(pokemon);
                }
            }

            // If no pokemon is found throw "No pokemon" error
            if (foundPokemons.isEmpty())
                throw new IOException("No Pokemon with name " + name + " exist.");

            // if mutiple pokemon found, delete pokemon based on ids
            if (foundPokemons.size() > 1) {

                // Print out all pokemon found with that name.
                for (Pokemon pokemon : foundPokemons) {
                    System.out.println("\t" + pokemon.getName() + " with ID: " + pokemon.getID());
                }

                // Prompt user to enter pokemon ID to be deleted
                System.out.print("Enter ID to delete: ");
                String strID = scnr.nextLine();

                // Validate the entered ID is a number, parse and chec if Id exist.
                if (isStrANumber(strID)) {
                    int ID = Integer.parseInt(strID);
                    for (Pokemon pokemon : foundPokemons) {
                        if (pokemon.getID() == ID) {
                            pokemonToDelete = pokemon;
                        }
                    }
                }

                // if no pokemon is found then throw error.
                if (pokemonToDelete == null) {
                    throw new IOException("No " + name + " Pokemon with ID " + strID + " exist.");
                }
            } else {
                // if only one pokemon is found then assign pokemon to be deleted.
                pokemonToDelete = foundPokemons.get(0);
            }

            // Remove pokemon from list of caught pokemon and print success message.
            pokemons.remove(pokemonToDelete);
            System.out.printf("%s with ID %d has been deleted from your PC!\n", pokemonToDelete.getName(),
                    pokemonToDelete.getID());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * Displays a list of all Pokemon currently held in the user's PC
     * Input: No Input;
     * Output: Prints to the console a formatted list of Pokemon, including their
     * names, IDs, levels, types, and moves, separated by dividers for clarity.
     * Parameters accepted: None.
     * Value returned: None (void method);
     *
     */
    public static void printPCPokemon() {
        String divider = "-".repeat(50);
        System.out.println("You currently have the following Pokemon:");
        System.out.println(divider);

        // Iterate to print every pokemon details in PC;
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon.toString());
            System.out.println(divider);
        }
    }

    /**
     * Retrieves and displays information for a specified Pokemon from a journal
     * file.
     * Input: User input for the name of the Pokemon whose information is to be
     * printed.
     * Output: Prints to the console detailed information of the specified Pokemon,
     * including its name, types, and moves, if the corresponding journal file
     * exists.
     * Parameters accepted: No paramter;
     * Value returned: None (void method)
     *
     */
    public static void printJournalPokemon() {
        try {
            // Prompt the user to enter the name and conver it to lower case for easy match.
            System.out.print("What Pokemon do you want to print the information of?: ");
            String name = scnr.nextLine();
            name = name.toLowerCase();

            // Generate file name and search for file in list of journals.
            String fileName = name + ".txt";
            if (!journals.contains(fileName)) {
                throw new IOException(name + " not found in your journals."); // throw error if journal not found.
            }

            // Initialize File reader and buffer reader to read file content.
            FileReader reader = new FileReader(fileName);
            BufferedReader buffReader = new BufferedReader(reader);

            // Split pokemon into two part by spliting string;
            String pokemon = buffReader.readLine();
            String[] pokemonStr = pokemon.split(":");

            // Process the first section of pokemon info which consit of name, level, and
            // types.
            String[] pokemonStr1 = pokemonStr[0].split(",");
            String pokemonName = pokemonStr1[0].trim();
            ArrayList<String> types = new ArrayList<String>();
            for (int i = 2; i < pokemonStr1.length; i++) {
                types.add(pokemonStr1[i].trim());
            }

            // Process the second section of pokemon info which consit of moves if its not
            // empty.
            ArrayList<String> moves = new ArrayList<String>();
            if (pokemonStr.length > 1) {
                String[] pokemonStr2 = pokemonStr[1].split(",");
                for (int i = 0; i < pokemonStr2.length; i++) {
                    moves.add(pokemonStr2[i].trim());
                }
            }

            // Print the found jornal resouces.
            System.out.println("Pokéman: " + pokemonName + "\nType(s): " + String.join(", ", types) + "\nMoves: "
                    + String.join(", ", moves));

            // Close journal file.
            buffReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Attempts to catch a new Pokemon and offers the user the choice to keep it. If
     * kept, the Pokemon is added to the user's collection.
     * Input: Requires the total number of lines in the Pokemon source file to
     * randomly select a Pokemon.
     * Parameters accepted: int pokemonLineCount - the total number of lines in the
     * Pokemon source file.
     * Value returned: None (void method);
     */
    public static void catchNewPokemon(int pokemonLineCount) {
        try {
            final String YES = "y";

            // Get random pokemon data in pokeon file and trim the string of white spaces.
            String pokemon = getRandomPokemon(pokemonLineCount);
            pokemon = pokemon.trim();

            // Check line fetch is empty and throw error.
            if (pokemon.equals("") || pokemon.equals(null)) {
                throw new IOException("Failed to catch a Pokémon");
            }

            // Split pokemon into two part to get pokemon details
            String[] pokemonStr = pokemon.split(":");

            // Process the first section of pokemon info which consit of name, level, and
            // types.
            String[] pokemonStr1 = pokemonStr[0].split(",");
            String name = pokemonStr1[0].trim();
            int level = Integer.parseInt(pokemonStr1[1].trim());
            ArrayList<String> types = new ArrayList<String>();
            for (int i = 2; i < pokemonStr1.length; i++) {
                types.add(pokemonStr1[i].trim());
            }

            // Process the second section of pokemon info which consit of moves if its not
            // empty.
            ArrayList<String> moves = new ArrayList<String>();
            if (pokemonStr.length > 1) {
                String[] pokemonStr2 = pokemonStr[1].split(",");
                for (int i = 0; i < pokemonStr2.length; i++) {
                    moves.add(pokemonStr2[i].trim());
                }
            }

            // Print success message if successfully catch a pokemon and add it the the
            // journal.
            System.out.printf("You caught a level %s %s!\n", level, name);
            addJournal(name, pokemon, moves);

            // Prompt user to decide if they want to add pokemon to PC or not.
            System.out.print("Would you like to keep the Pokemon (y/n)? ");
            String keep = scnr.nextLine();
            Pokemon pokemonToAdd = null;

            // if yes then add to PC and then if otherwise do nothing.
            if (keep.equals(YES)) {
                try {
                    // Attempts to create a new pokemon
                    pokemonToAdd = new Pokemon(name, level, types, moves);
                    pokemons.add(pokemonToAdd);
                } catch (IllegalStateException e) { // Catches exceptionf from constructor

                    // Informs the user that the pokemon doesnt have any moves and "Struggle" will
                    // be added by default.
                    System.out.println("Uh oh! The " + name
                            + " doesn't have any moves. The Pokemon will be added to your PC with the single move \"Struggle\" ");
                    moves.add("Struggle");
                    pokemonToAdd = new Pokemon(name, level, types, moves);
                    pokemons.add(pokemonToAdd);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * Manages the journal entry for a Pokemon, creating or updating it with
     * provided details and moves. Alerts on new entries or updates.
     * Input: Pokemon name, detail string, and moves.
     * Output: Updates or creates a journal file, logging actions or errors to the
     * console.
     * Parameters: name (String), newPokemon (String), newMoves (ArrayList<String>).
     * Returns: None.
     *
     */
    public static void addJournal(String name, String newPokemon, ArrayList<String> newMoves) {
        try {
            // Generate journal file name and reassign pokemon to to be upserted.
            String journalName = name.toLowerCase() + ".txt";
            String pokemonToUpsert = newPokemon;
            ArrayList<String> movesToUpsert = new ArrayList<String>(newMoves);

            // Check if journal has already been created for journal
            if (!journals.contains(journalName)) {
                System.out.println("This is a new Pokemon, a new journal page will be added.");
                journals.add(journalName);
            } else {
                // Initialize file reader and buffer reader to read file content.
                FileReader reader = new FileReader(journalName);
                BufferedReader buffReader = new BufferedReader(reader);

                String pokemon = buffReader.readLine();
                String[] pokemonStr = pokemon.split(":");

                // if pokemon has moves then update then add moves if doesnt exist.
                if (pokemonStr.length > 1) {
                    String[] moveStr = pokemonStr[1].split(",");
                    for (String move : moveStr) {
                        String trimmedMove = move.trim();

                        // Add non duplicate moves.
                        if (!movesToUpsert.contains(trimmedMove)) {
                            movesToUpsert.add(trimmedMove);
                        }
                    }
                }

                // Combine all new moves and add it.
                pokemonStr[1] = String.join(", ", movesToUpsert);
                pokemonToUpsert = String.join(": ", pokemonStr);
                buffReader.close();
            }
            // Initailze buffer reader and update or create pokemon journal.
            FileWriter fileWriter = new FileWriter(journalName);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);
            buffWriter.write(pokemonToUpsert);
            buffWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Calculates the total number of lines in the Pokémon database, filtering out
     * empty lines.
     * Input: Reads from a predefined Pokémon database file (POKEMON_DB).
     * Output: Prints error messages if file issues occur.
     * Parameters: None.
     * Returns: Total line count of the database, excluding empty lines, or 0 if
     * errors occur or no Pokémon are found.
     *
     */
    public static int getPokemonLineCount() {
        int pokemon = 0;
        int pokemonLine = 0;
        try {
            // Initilaze file reader to read file content.
            FileReader reader = new FileReader(POKEMON_DB);
            BufferedReader buffReader = new BufferedReader(reader);
            String buffer = buffReader.readLine();

            // Check each line to count num of pokemon and linees in given file.
            while (buffer != null) {
                if (!buffer.trim().isEmpty()) {
                    pokemon++;
                }
                pokemonLine++;
                buffer = buffReader.readLine();
            }

            // Check if pokemon was found in file.
            if (pokemon < 1) {
                buffReader.close();
                pokemonLine = 0;
                throw new IOException("No Pokémon found in this area, please try your Pokémon journey elsewhere!");
            }
            buffReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pokemonLine;
    }

    /**
     * Retrieves a random Pokémon entry from the database.
     * Input: Total number of Pokémon (int totalPokemon) to calculate random
     * selection.
     * Output: Error messages for file issues. Returns a random Pokémon entry as a
     * String, or null on error.
     * Parameters: totalPokemon - Total Pokémon entries count.
     * Returns: Random Pokémon entry (String) or null.
     *
     */
    public static String getRandomPokemon(int totalPokemon) {
        String pokemon = null;
        try {

            // Initializes reading of the POKEMON_DB file to select a random Pokémon entry.
            FileReader reader = new FileReader(POKEMON_DB);
            BufferedReader buffReader = new BufferedReader(reader);
            if (buffReader.ready()) {
                // Generate randmon line to select pokemon.
                int randLine = (int) (1 + (Math.random() * totalPokemon));
                pokemon = buffReader.readLine();

                // Inialize pointer and count until radmon line is reached to check pokemon at
                // that line.
                int pointer = 1;
                while (pokemon != null && pointer < randLine) {
                    pokemon = buffReader.readLine();
                    pointer++;
                }
            }
            buffReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pokemon;
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
        for (int i = 1; i <= menuStrings.length; i++) {
            System.out.println(i + " - " + menuStrings[i - 1]);
        }
        System.out.printf("What would you like to do (1-%d)? ", menuStrings.length);

        String selected = scnr.nextLine();
        return selected;
    }

}
