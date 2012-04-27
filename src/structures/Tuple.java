package structures;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Tuple implements WritableComparable<Tuple>{
    private int p;
    private int q;
    public Tuple(){};
    
	public Tuple(int p, int q) {
        this.p = p;
        this.q = q;
    }
    
    public int getP() {
        return p;
    }

    public void setP(int p) {
		this.p = p;
	}

	public void setQ(int q) {
		this.q = q;
	}

	public int getQ() {
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
        p = in.readInt();
        q = in.readInt();
    }
    
    @Override
    public String toString(){
    	return Integer.toString(p) + " " + Integer.toString(q);
    }
    
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(p);
        out.writeInt(q);
    }
    
    @Override
    public String toString(){
    	return this.p + " " + this.q;
    }
}
