import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A testing library that supports multiple test cases.
 * 
 * Logs assertion results internally for overall success reporting. Allows use
 * of a global
 * counter to keep stats across multiple test classes.
 * 
 * NOTE TO CURIOUS STUDENTS: You do not need to know what this is doing or
 * how/why it works. This will be covered in future courses.
 */
public class TestLib {
    public interface Executable {
        void execute() throws Throwable;
    }

    public static class TestCounter {
        String name;
        int assertionsPassed;
        int assertionsFailed;

        public TestCounter(String name) {
            this.name = name;
            this.assertionsPassed = 0;
            this.assertionsFailed = 0;
        }

        public void printResults() {
            System.out.println("Summary of " + name + " tests:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "\tAssertions passed: " + assertionsPassed);
            System.out.println(ConsoleColors.RED + "\tAssertions failed: " + assertionsFailed);
            int pct = (assertionsPassed * 100) / (assertionsPassed + assertionsFailed);
            System.out.println(ConsoleColors.CYAN + "\tPassing ratio: " + pct + "%\n"
                    + ConsoleColors.RESET);
        }
    }

    static String testType = "basic";
    static String testCaseName = "";
    static int testCaseCount = 0;
    static int testCaseAssertionIndex = 1;
    static TestCounter counter;
    static TestCounter globalCounter;

    public static void initializeTestSuite(String testType, TestCounter globalCounter) {
        counter = new TestCounter(testType);
        TestLib.globalCounter = globalCounter;
        testCaseCount = 0;

        System.out.println(ConsoleColors.PURPLE + "Running " + testType + " tests..." + ConsoleColors.RESET);
    }

    public static void initializeTestCase(String name) {
        testCaseName = name;
        testCaseCount++;
        testCaseAssertionIndex = 1;
        System.out.println(ConsoleColors.YELLOW + "Test Case " + testCaseCount + ": " +
                testCaseName + ConsoleColors.RESET);
    }

    public static void finalizeTestSuite() {
        counter.printResults();
        if (globalCounter != null) {
            globalCounter.assertionsPassed += counter.assertionsPassed;
            globalCounter.assertionsFailed += counter.assertionsFailed;
        }
    }

    public static boolean assertTrue(boolean b) {
        if (!b) {
            printFail("Expected true.");
        }
        return logAssertion(b);
    }

    public static boolean assertFalse(boolean b) {
        if (b) {
            printFail("Expected false.");
        }
        return logAssertion(!b);
    }

    public static boolean assertNotNull(Object o) {
        if (o == null) {
            printFail("Expected non-null value.");
        }
        return logAssertion(o != null);
    }

    public static boolean assertNull(Object o) {
        if (o != null) {
            printFail("Expected null value.");
        }
        return logAssertion(o == null);
    }

    public static <V extends Comparable<T>, T> boolean assertEqual(V o1, V o2) {
        if (!o1.equals(o2)) {
            printFail(String.format("Two values are not equal.\n\t%s\n\t%s",
                    o1.toString(), o2.toString()));
        }
        return logAssertion(o1.equals(o2));
    }

    public static <V extends Comparable<T>, T> boolean assertNotEqual(V o1, V o2) {
        if (o1.equals(o2)) {
            printFail(String.format("Two values are equal.\n\t%s\n\t%s",
                    o1.toString(), o2.toString()));
        }
        return logAssertion(!o1.equals(o2));
    }

    public static <T extends Throwable> boolean assertThrows(Class<T> expectedType, Executable executable) {
        try {
            executable.execute();
        } catch (Throwable thrown) {
            if (expectedType.equals(thrown.getClass())) {
                printSuccess("Threw the appropriate exception: " + expectedType.getCanonicalName());
                return logAssertion(true);
            } else {
                printFail(String.format("Did not throw %s, threw %s instead.", expectedType.getCanonicalName(),
                        thrown.getClass().getCanonicalName()));
                return logAssertion(false);
            }
        }

        printFail("Did not throw type " + expectedType.getCanonicalName());
        return logAssertion(false);
    }

    public static boolean assertDoesNotThrow(Executable executable) {
        try {
            executable.execute();
        } catch (Throwable thrown) {
            printFail("Did threw unexpected exception: " + thrown.getClass().getCanonicalName());
            return logAssertion(false);
        }

        // printSuccess("Didn't throw any exceptions.");
        return logAssertion(true);
    }

    /**
     * NOTE TO CURIOUS STUDENTS: For those of you still pokinga around, checking strings
     * output to the console is generally considered _bad practice_, as it is very
     * fragile and is usually quite subjective (there's no one "right" output!) This
     * is something we do in some early courses because we don't quite have all the
     * tools necessary to avoid it.
     */
    public static boolean assertOutputContains(Executable executable, String regex) {
        boolean valid = true;

        String output = executeWithCapture(executable);
        if (output != null && !stringContains(output, regex)) {
            printFail("Console output did not contain " + regex);
            valid = false;
        }

        return logAssertion(valid);
    }

    public static boolean assertOutputDoesNotContain(Executable executable, String regex) {
        boolean valid = true;

        String output = executeWithCapture(executable);
        if (output != null && stringContains(output, regex)) {
            printFail("Console output contains " + regex);
            valid = false;
        }

        return logAssertion(valid);
    }

    public static String executeWithCapture(Executable executable) {
        String output = null;
        ByteArrayOutputStream outCapture = new ByteArrayOutputStream();
        try {
            PrintStream stdout = System.out;
            System.setOut(new PrintStream(outCapture));
            executable.execute();
            // Restore original stdout stream, and print captured message to
            // aid in clarity and debugging.
            System.setOut(stdout);
            output = outCapture.toString();
            System.out.print(output);
        } catch (Throwable e) {
            printFail("Unexpected error: " + e);
        }
        return output;
    }

    public static boolean stringContains(String s1, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s1);
        return matcher.find();
    }

    private static void printSuccess(String msg) {
        System.out.println(ConsoleColors.GREEN + "SUCCESS " + testCaseLabel() + ": " + msg + ConsoleColors.RESET);
    }

    private static void printFail(String msg) {
        System.out.println(ConsoleColors.RED + "FAILURE " + testCaseLabel() + ": " + msg + ConsoleColors.RESET);
    }

    private static boolean logAssertion(boolean result) {
        testCaseAssertionIndex++;
        if (result) {
            counter.assertionsPassed++;
        } else {
            counter.assertionsFailed++;
        }

        return result;
    }

    private static String testCaseLabel() {
        return String.format("(%s:%d)", testCaseName, testCaseAssertionIndex);
    }
}
