package step1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import constants.Constants;

public class GroupPartitioner extends Partitioner<IntWritable, IntWritable> {

    @Override
    public int getPartition(IntWritable key, IntWritable value, int numGroups) {
        return key.get() % numGroups;
    }

}
