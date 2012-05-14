package structures;

import constants.Constants;

public class MatrixUtilities {
    private MatrixUtilities() {}

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
     * Computes the primary column group for a position given a M value and a column. </br> </br>
     * NOTE: if the column is a boundary column, this will give its primary column group. To find
     * its secondary column group, subtract 1 from the result.
     * 
     * @param M
     *            The # of columns. Use Constants.M
     * @param G
     *            The nominal number of columns per group. Use Constants.g
     * @param column
     *            The number of the column whose group you're looking for.
     * @return
     */
    public static int getColumnGroup(final int M, final int G, final int column) {
        if (column < 0)
            return 0;
        final int column_group = column / G;
        return column_group;
    }

    /**
     * Returns true if the column in which this position is contained is a boundary column. (ie if
     * this column num divides g)
     * 
     * @param position
     * @return Returns true if the
     */
    public static boolean isBoundary(final int position) {
        final int M = Constants.M;
        final int g = Constants.g;
        final int column = MatrixUtilities.getColumn(M, position);

        // a strange edge case, where g==1. Not likely to actually happen,
        // but...
        if ((column == 0) && (Constants.g == 1))
            return true;

        if ((column != 0) && ((column % g) == 0))
            return true;
        else
            return false;
    }

    public static int minInGroup(final int column_group) {
        return (Constants.M * Constants.g * column_group) + 1;
    }

    public static int maxInGroup(final int column_group) {
        final int num_of_groups = (Constants.M / Constants.g);
        int max;

        max = Constants.M * Constants.g * (column_group + 1);

        // If the passed group is not the last group add the number of
        // groups minus 1 to the output
        if (column_group != (num_of_groups - 1))
            max += Constants.M;

        return max;
    }
}
