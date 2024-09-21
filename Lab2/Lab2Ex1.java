public class Lab2Ex1 {


    public static void main(String[] args) {
        final int SEED = 301524;
        int score = 0;

        int current, demicalPlace, digit, count, previousDigits;

        current = SEED;
        demicalPlace = 1000000000;
        count = 9;
        previousDigits = 0;

        while (demicalPlace != 0) {
            digit = current / demicalPlace;


           switch (digit) {
            case 0:
                break;
            case 1:
                method1();
                break;
            case 2:
                score += method2(count);
                break;
            case 3: 
                score += method3(previousDigits % 2 != 0);
                break;
            case 4: 
                score += method4((int)(Math.random() * 40 + 10), (int)(Math.random() * 40 + 10));
                break;
            default:
                System.out.printf("Unknown location: %d", digit);
                break;
           }
        }

    }

    static void method1 () {
        System.out.println("You are standing at the end of a road before a small brick building.");
    }

    static int method2 (int n) {
        System.out.printf("You are in a maze of %d twisty little passages, all alike.", n);
        return -1 * (n / 2);
    }

    static int method3 (boolean isTrue) {
        int response;
        if(isTrue) {
            response = 3;
            System.out.println("You are on the edge of a breath-taking view.");
        } else {
            response = -3;
            System.out.println("It is pitch black. You are likely to be eaten by a grue.");
        }
        return response;
    }

    static double method4(int width, int depth) {
        System.out.printf("You are in a north/south canyon about %d feet across and %d feet deep.", width, depth);
        return (width * depth) / 100;
    }
}
