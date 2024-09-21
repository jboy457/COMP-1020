public class ColumnarOutput {
    public static void main(String[] args) {
        final int START = 0;
        final int END = 10752;
        final int INCREMENT = 256;
        final int COLUMNS = 8;
        final boolean RIGHT_JUSTIFY = true;

        int pointer = 0;
        for(int i = START; i <= END; i+= INCREMENT) {
            System.out.printf("%6s", i);
            pointer++;
            if(pointer == COLUMNS) {
                System.out.println();
                pointer = 0;
            }
        }
    }
}
