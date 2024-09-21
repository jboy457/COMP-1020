public class Lab2Ex3 {
  public static void main(String[] args) {
    int[] array1 = { 3, 9, 7 }, array2 = { 15, 3, 4, 7 }, array3 = { 10, 8, 6, 4, 5, 15};
    int[] arrayTemp;
    int[] arrayFinal;
		
    arrayTemp = concatenateArrays(array1, array2);
    arrayFinal = concatenateArrays(arrayTemp, array3);
		
    for (int i = 0; i < arrayFinal.length; i++) {
      System.out.println(arrayFinal[i]);
    }
		
    System.out.println("End of processing.");
  }
	
  public static int[] concatenateArrays(int[] a1, int[] a2) {
    int[] a3 = new int[a1.length + a2.length];

    for (int i = 0; i < a1.length; i++) {
      // ???
      a3[i] = a1[i];
    }
    for (int i = 0; i < a2.length; i++) {
      // ???
      a3[i + a1.length] = a2[i];
    }

    return a3;
  }
}
