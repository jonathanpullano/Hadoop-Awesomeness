package structures;

import java.io.Serializable;

public class Tuple implements Serializable, Comparable<Tuple>{
    private static final long serialVersionUID = -4348248931257442036L;
    private int p;
    private int q;
    private int g;
    
    public Tuple(int p, int q) {
        this.p = p;
        this.q = q;
    }
    
    public Tuple(int g, int p, int q) {
        this(p, q);
        this.g = g;
    }
    
    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public int getG() {
        return g;
    }

    @Override
    public int compareTo(Tuple that) {
        if(this == that) return 0;
        return this.p - that.p;
    }
}
