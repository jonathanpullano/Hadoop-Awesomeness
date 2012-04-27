package structures;

import constants.Constants;

public class MatrixUtilities {
    private MatrixUtilities() {
    }

    /**
     * Computes the column for a position given a M value and a position
     * 
     * @param M
     * @param p
     * @return
     */
    public static int getColumn(final int M, final int p) {
        return (p - 1) / M;
    }

    /**
     * Computes the column group for a position given a M value and a column
     * 
     * @param M
     * @param G
     * @param column
     * @return
     */
    public static int getColumnGroup(final int M, final int G, final int column) {
        int column_group = column / (M / G);
        if (column_group == G) column_group--;
        return column_group;
    }

    public static int isBoundary(final int position) {
        final int M = Constants.M;
        final int g = Constants.g;
        final int col = MatrixUtilities.getColumn(M, position);

        // first or last columns cannot be boundaries
        if ((col == 0) || (col == M - 1)) return 0;

        // get the col group for this position
        final int column_group = getColumnGroup(M, g, col);

        if (column_group != getColumnGroup(M, g, col - 1)) return -1;
        if (column_group != getColumnGroup(M, g, col + 1))
            return 1;
        else return 0;
    }
}
