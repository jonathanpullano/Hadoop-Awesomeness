package structures;

import java.nio.ByteBuffer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparator;

public class IntComparator extends WritableComparator {

    public IntComparator() {
        super(IntWritable.class);
    }

    @Override
    public int compare(final byte[] b1, final int s1, final int l1,
            final byte[] b2, final int s2, final int l2) {

        final Integer v1 = ByteBuffer.wrap(b1, s1, l1).getInt();
        final Integer v2 = ByteBuffer.wrap(b2, s2, l2).getInt();

        return v1.compareTo(v2) * (-1);
    }
}