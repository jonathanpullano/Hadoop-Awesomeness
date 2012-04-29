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
		if (column < 0) {
			return 0;
		}
		final int column_group = column / G;
		return column_group;
	}

	public static boolean isBoundary(final int position) {
		final int M = Constants.M;
		final int g = Constants.g;
		final int column = MatrixUtilities.getColumn(M, position);

		if ((column != 0) && ((column % g) == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static int minInGroup(final int column_group) {
		return (Constants.M * Constants.g * column_group) + 1;
	}

	public static int maxInGroup(final int column_group) {
		final int num_of_groups = (Constants.M / Constants.g);
		int max;

		max = Constants.M * Constants.g * (column_group + 1);

		// If the passed group is not the last group add M to the output
		if (column_group != (num_of_groups - 1)) {
			max += Constants.M;
		}

		return max;
	}
}
