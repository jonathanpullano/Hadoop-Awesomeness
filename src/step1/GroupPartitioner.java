package step1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class GroupPartitioner extends Partitioner<IntWritable, IntWritable> {

    @Override
    public int getPartition(final IntWritable key, final IntWritable value,
            final int numGroups) {
        return key.get() % numGroups;
    }

}
