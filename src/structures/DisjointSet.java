package structures;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author jonathan
 */
public class DisjointSet {
	// key=child; val=parent
	private final HashMap<Integer, Integer> parent;

	/**
	 * Constructor
	 */
	public DisjointSet() {
		parent = new HashMap<Integer, Integer>();
	}

	/**
	 * Constructor ESTIMATED group size. It's better to guess low.
	 */
	public DisjointSet(final int size) {
		parent = new HashMap<Integer, Integer>(size);
	}

	/**
	 * Union operation
	 */
	public void union(final int p1, final int p2) {
		final int parent1 = find(p1);
		final int parent2 = find(p2);

		if (parent1 >= parent2) {
			parent.put(parent1, parent2);
		} else {
			parent.put(parent2, parent1);
		}
	}

	/**
	 * @return true if the given coord comes from the same component, or false
	 *         otherwise
	 */
	public boolean isConnected(final int p1, final int p2) {
		return find(p1) == find(p2);
	}

	/**
	 * Gets the parent of a node
	 */
	public int parentOf(final int p) {
		if (parent.containsKey(p)) {
			return parent.get(p);
		}
		return p;
	}

	/**
	 * Returns the parent that identifies this component
	 */
	public int find(int p) {
		final HashSet<Integer> examined = new HashSet<Integer>();
		examined.add(p);
		while (p != parentOf(p)) {
			p = parentOf(p);
			examined.add(p);
		}

		// Auto-balance
		for (final Integer e : examined) {
			parent.put(e, p);
		}
		return p;
	}
}
