package structures;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Tuple implements WritableComparable<Tuple> {
    private int p;
    private int q;

    public Tuple() {
    };

    public Tuple(final int p, final int q) {
        this.p = p;
        this.q = q;
    }

    public Tuple(final Tuple t) {
        this.p = t.p;
        this.q = t.q;
    }

    public int getP() {
        return p;
    }

    public void setP(final int p) {
        this.p = p;
    }

    public void setQ(final int q) {
        this.q = q;
    }

    public int getQ() {
        return q;
    }

    @Override
    /**
     * Sort on P, then on Q
     */
    public int compareTo(final Tuple that) {
        if (this == that) return 0;
        final int pDiff = this.p - that.p;
        if (pDiff == 0) return this.q - that.q;
        return pDiff;
    }

    @Override
    public void readFields(final DataInput in) throws IOException {
        p = in.readInt();
        q = in.readInt();
    }

    @Override
    public String toString() {
        return Integer.toString(p) + " " + Integer.toString(q);
    }

    @Override
    public void write(final DataOutput out) throws IOException {
        out.writeInt(p);
        out.writeInt(q);
    }
}
