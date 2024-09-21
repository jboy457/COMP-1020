public class Lab2Ex2 {
  public static void main(String[] args) {
    int[] array1 = { 3, 9, 7 }, array2 = { 15, 3, 4, 7 }, array3 = { 10, 8, 6, 4, 5, 15};
    int[] arrayTemp = new int[array1.length + array2.length];
    int[] arrayFinal = new int[arrayTemp.length + array3.length];
		
    concatenateArrays(array1, array2, arrayTemp);
    concatenateArrays(arrayTemp, array3, arrayFinal);

    for (int i = 0; i < arrayFinal.length; i++) {
      System.out.println(arrayFinal[i]);
    }
		
    System.out.println("End of processing.");
  }
	
  public static void concatenateArrays(int[] a1, int[] a2, int[] a3) {
    // a3 = new int[a1.length + a2.length];
    int pos1 = 0, pos2 = 0;
		
    for (int i = 0; i < a3.length; i++) {
      if (pos2 > 0) {
        a3[i] = a2[pos2];
        pos2++;
      } else {
        if (pos1 >= a1.length) {
          a3[i] = a2[0];
          pos2++;
        } else {
          a3[i] = a1[pos1];
          pos1++;
        }
      }
    }
  }
}
