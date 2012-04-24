/**
 * @author jonathan
 */
public class DisjointSet {
    private Coord[][] parents;

    /**
     * Constructor
     * @param xSize x dimension of this graph
     * @param ySize y dimension of this graph
     */
    public DisjointSet(int xSize, int ySize) {
        parents = new Coord[xSize][ySize];
        for(int i = 0; i < xSize; i++)
            for(int j = 0; j < ySize; j++)
                parents[i][j] = new Coord(i, j);
    }

    /**
     * Union operation
     */
    public void union(int x1, int y1, int x2, int y2) {
        Coord parent1 = find(x1, y1);
        Coord parent2 = find(x2, y2);
        if(parent1.compareTo(parent2) >= 0) {
            parents[parent1.x][parent1.y] = parent2;
        } else {
            parents[parent2.x][parent2.y] = parent1;
        }
    }

    /**
     * Returns the parent that identifies this component
     */
    public Coord find(int x, int y) {
        Coord parent = new Coord(x,y);
        while(!parents[x][y].equals(parent)) {
            parent = parents[x][y];
        }
        return parent;
    }

    /**
     * @return true if the given coord comes from the same component,
     *         or false otherwise
     */
    boolean sameComponent(int x1, int y1, int x2, int y2) {
        return find(x1, y1) == find(x2, y2);
    }

    /**
     * @return true if the given coord comes from the same component,
     *         or false otherwise
     */
    boolean sameComponent(Coord c1, Coord c2) {
        return sameComponent(c1.getX(), c1.getY(), c2.getX(), c2.getY());
    }

    /**
     * Represents a Coordinate from this Disjoint set
     * @author jonathan
     */
    private final class Coord implements Comparable<Coord> {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
        
        @Override
        public boolean equals(Object that) {
            if(this == that) return true;
            if(!(that instanceof Coord)) return false;
            Coord thatCoord = (Coord)that;
            return this.x == thatCoord.x && this.y == thatCoord.y;
        }
        
        private int linearizePosition() {
            return x * parents.length + y;
        }

        @Override
        public int hashCode() {
            return linearizePosition();
        }

        @Override
        public int compareTo(Coord that) {
            if(this == that) return 0;
            return linearizePosition() - that.linearizePosition();
        }
    }
}
