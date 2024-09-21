public class Test {
    public static void main(String[] args) {
        test();
    }


    public static void test() {
        int [][] name = new int[5][4];

        for(int i = 0; i < name.length; i++) {
            for(int j = 0; j < name[i].length; j++) {
                name[i][j] = (int)(Math.random() * 100);
            }
        }

        // for(int i = 0; i < name.length; i++) {
        //     for(int j = 0; j < name[i].length; j++) {
        //         System.out.print(name[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        for(int[] x : name) {
            for(int y : x) {
                System.out.print(y+ " ");
            }
            System.out.println();
        }
    }
}
