package structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrixPrinter {

    private static final int[][] matrix = new int[40][40];

    public static void main(final String args[]) {
        try {
            final FileReader input = new FileReader(
                    "data/appendix_test_files/data6.txt");

            final BufferedReader bufRead = new BufferedReader(input);

            String line = ""; // String that holds current file line
            int N = 0; // Line number of count

            while (line != null) {
                N++;
                line = bufRead.readLine();
                if (line == null)
                    break;
                final float val = Float.parseFloat(line);
                final int sq = (int) Math.sqrt(N);
                final int diff = N - (sq * sq);
                int col, row;

                if (diff == 0) {
                    col = sq - 1;
                    row = sq - 1;
                } else
                    if ((diff % 2) == 0) {
                        col = sq;
                        row = (diff - 1) / 2;
                    } else {
                        col = (diff - 1) / 2;
                        row = sq;
                    }
                if (val > 0)
                    matrix[col][row] = 1;
                else
                    matrix[col][row] = 0;
            }

        } catch (final IOException e) {
            // If another exception is generated, print a stack trace
            e.printStackTrace();
        }

        printMatrix();
    }

    public static void printMatrix() {
        for (int row = 39; row > -1; row--) {
            for (int col = 0; col < 40; col++)
                System.out.print(((matrix[col][row])) + " ");
            System.out.println("");
        }
    }

}
