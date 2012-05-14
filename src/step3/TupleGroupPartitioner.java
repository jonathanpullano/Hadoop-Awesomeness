package step3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import structures.Tuple;

public class TupleGroupPartitioner extends Partitioner<IntWritable, Tuple> {

    @Override
    public int getPartition(final IntWritable key, final Tuple value,
            final int numGroups) {
        return key.get() % numGroups;
    }

}
