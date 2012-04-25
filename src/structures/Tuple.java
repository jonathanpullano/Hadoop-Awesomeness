package structures;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

public class Tuple implements WritableComparable<Tuple>{
    private long p;
    private long q;
    
    public Tuple(long p, long q) {
        this.p = p;
        this.q = q;
    }
    
    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    @Override
    public int compareTo(Tuple that) {
        if(this == that) return 0;
        //Can't just return subtraction because of int overflow
        long diff = this.p - that.p;
        if(diff > 0) return 1;
        if(diff < 0) return -1;
        return 0;
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        p = in.readLong();
        q = in.readLong();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(p);
        out.writeLong(q);
    }
}
