package structures;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import structures.Tuple;

public class TupleComparator extends WritableComparator {

    public TupleComparator() {
        super(Tuple.class);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
    	return a.compareTo(b);
    }
}