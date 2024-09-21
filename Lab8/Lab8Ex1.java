public class Lab8Ex1 {
  public static void main(String args[]) {
    int[] list;

    list = createList();
    printList(list);
    System.out.println("\nThe maximum value is: " + findMax(list));

    System.out.println("\nEnd of processing.");
  }

  public static int[] createList() {
    int[] list = { 20,10,50,60,70,40,30 };

    return list;
  }

  public static int findMax(int[] values) {
    return findMaxRecursive(values, 0);
  }

  public static int findMaxRecursive(int[] values, int position) {
    int result = 0;

    if (position == values.length - 1) {
      // Do something here???
    } else {
      result = findMaxRecursive(values, position + 1);
      
      // And maybe some kind of if statement here???
    }

    return result;
  }

  public static void printList(int[] array) {
    printArrayRecursively(array, 0);
  }

  public static void printArrayRecursively(int[] array, int pos) {
    if (pos == array.length-1) {
      System.out.println(array[pos]);
    } else {
      System.out.print(array[pos] + " ");
      printArrayRecursively(array, pos+1);
    }
  }
}