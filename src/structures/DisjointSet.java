package structures;

import java.util.HashSet;

/**
 * @author jonathan
 */
public class DisjointSet {
    //TODO: Memory optimize
    private int[] parent;

    /**
     * Constructor
     * @param xSize x dimension of this graph
     * @param ySize y dimension of this graph
     */
    public DisjointSet(int size) {
        if(size == 0) throw new IndexOutOfBoundsException("Invalid Size");
        parent = new int[size];
        for(int i = 0; i < size; i++)
            parent[i] = i;
    }

    /**
     * Union operation
     */
    public void union(int p1, int p2) {
        int parent1 = find(p1);
        int parent2 = find(p2);
        
        if(p1 >= p2) 
            parent[parent1] = parent2;
        else
            parent[parent2] = parent1;
    }
    
    /**
     * @return true if the given coord comes from the same component,
     *         or false otherwise
     */
    public boolean isConnected(int p1, int p2) {
        return find(p1) == find(p2);
    }
    
    /**
     * Returns the parent that identifies this component
     */
    public int find(int p) {
        while(p != parent[p]) p = parent[p];
        HashSet<Integer> examined = new HashSet<Integer>();
        for(Integer e : examined) {
            parent[e] = p;
        }
        return p;
    }
}
