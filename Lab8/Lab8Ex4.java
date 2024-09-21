public class Lab8Ex4 {
  public static void main(String args[]) {
    printTriangle("*", 9);

    System.out.println("\nEnd of processing.");
  }
 
  public static void printTriangle(String text, int depth) {
    String line = text;
    int count;

    for (count=1; count <= depth; count++) {
      // HINT: instead of using a nested loop, try adding something to line
      System.out.println(line);
    }
  }
}