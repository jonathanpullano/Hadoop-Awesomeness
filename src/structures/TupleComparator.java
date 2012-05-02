package structures;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TupleComparator extends WritableComparator {

	public TupleComparator() {
		super(Tuple.class);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int compare(final WritableComparable a, final WritableComparable b) {
		return a.compareTo(b);
	}
}