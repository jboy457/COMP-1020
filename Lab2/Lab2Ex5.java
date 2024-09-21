public class Lab2Ex5 {
  public static void main(String[] args) {
    int[] array1 = { 3, 3, 9, 3, 7, 7 }, array2 = { 15, 3, 3, 4, 4, 7 }, array3 = { 7, 10, 8, 6, 4, 5, 15 }, array4 = { 9, 7, 3 };
    int[] arrayTemp;
    int[] arrayFinal;
		
    arrayTemp = combineArrays(array1, array2);
    arrayTemp = combineArrays(arrayTemp, array3);
    arrayFinal = combineArrays(arrayTemp, array4);
		
    for (int i = 0; i < arrayFinal.length; i++) {
      System.out.println(arrayFinal[i]);
    }
		
    System.out.println("End of processing.");
  }
	
  public static int[] combineArrays(int[] a1, int[] a2) {
    return a1;
  }
}
