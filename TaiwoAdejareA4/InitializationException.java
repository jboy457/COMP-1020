/**
 * A custom exception class strictly for use in this assignment to make things more readable.
 * 
 * NOTE TO CURIOUS STUDENTS: You do not need to know what this is doing or how/why it works.
 *      This will be covered in future courses.
 */

 public class InitializationException extends Exception { 
    public InitializationException(String message) {
        super(message);
    }
}
