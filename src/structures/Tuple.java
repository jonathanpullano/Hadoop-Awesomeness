package structures;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Tuple implements WritableComparable<Tuple> {
    private int first;
    private int second;

    public Tuple() {
    };

    public Tuple(final int p, final int q) {
        first = p;
        second = q;
    }

    public Tuple(final Tuple t) {
        first = t.first;
        second = t.second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(final int p) {
        first = p;
    }

    public void setSecond(final int q) {
        second = q;
    }

    public int getSecond() {
        return second;
    }

    @Override
    /**
     * Sort on P, then on Q
     */
    public int compareTo(final Tuple that) {
        if (this == that) return 0;
        final int pDiff = first - that.first;
        if (pDiff == 0) return second - that.second;
        return pDiff;
    }

    @Override
    public void readFields(final DataInput in) throws IOException {
        first = in.readInt();
        second = in.readInt();
    }

    @Override
    public String toString() {
        return Integer.toString(first) + " " + Integer.toString(second);
    }

    @Override
    public void write(final DataOutput out) throws IOException {
        out.writeInt(first);
        out.writeInt(second);
    }
}
