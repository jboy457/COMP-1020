import java.util.Scanner;

public class Lab1Example1 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String input;

    boolean keepGoing = true;
    int width, height;
    double areaRect, areaEllipse, diagonal, ratio;

    while (keepGoing) {

      System.out.print("Enter a width: ");
      width = in.nextInt();
      System.out.print("Enter a height: ");
      height = in.nextInt();
      System.out.printf("You entered width=%d, height=%d.\n", width, height);

      areaRect = width * height;
      areaEllipse = width * height * Math.PI;
      diagonal = Math.sqrt(width * width + width * height);
      ratio = (double) (width / height);

      System.out.printf("\nArea of a %d x %d rectangle = %f\n", width, height, areaRect);
      System.out.printf("Area of a %d x %d ellipse = %d\n", width, height, (int)areaEllipse);
      System.out.printf("Diagonal of a %d x %d right triangle = %f\n", width, height, diagonal);
      System.out.printf("Ratio of %d over %d = %f\n", width, height, ratio);

      in.nextLine();
      System.out.print("\nDo you want to process another (Y/N)? ");
      input = in.nextLine();
      System.out.printf("You entered '%s'.\n", input);
      if (!input.toUpperCase().startsWith("Y")) {
        keepGoing = false;
      }

      System.out.println("\n*** End of program. ***");
    }
  }
}